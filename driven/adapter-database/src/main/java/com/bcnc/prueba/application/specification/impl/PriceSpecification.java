package com.bcnc.prueba.application.specification.impl;

import com.bcnc.prueba.application.model.PriceMO;
import com.bcnc.prueba.application.specification.BaseSpecification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(staticName = "of")
public class PriceSpecification extends BaseSpecification<PriceMO> {

    private final OffsetDateTime dateTime;
    private final Long productId;
    private final Long brandId;

    @Override
    public Predicate toPredicate(Root<PriceMO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (dateTime != null) {
            predicates.add(searchByDateRange(dateTime, root, criteriaBuilder));
        }
        if (productId != null) {
            predicates.add(searchByProductId(productId, root, criteriaBuilder));
        }
        if (brandId != null) {
            predicates.add(searchByBrandId(brandId, root, criteriaBuilder));
        }
        return buildPredicate(criteriaBuilder);
    }

    private Predicate searchByBrandId(Long brandId, Root<PriceMO> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("brandId"), brandId);
    }

    private Predicate searchByProductId(Long productId, Root<PriceMO> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("productId"), productId);
    }

    private Predicate searchByDateRange(OffsetDateTime dateTime, Root<PriceMO> root, CriteriaBuilder criteriaBuilder) {
        if (dateTime != null) {

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), dateTime));
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), dateTime));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        }
        return criteriaBuilder.conjunction();
    }
}
