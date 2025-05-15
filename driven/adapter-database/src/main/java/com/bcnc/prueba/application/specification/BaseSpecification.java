package com.bcnc.prueba.application.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * @param <MO> Target model entity of the specificaiton
 */
public abstract class BaseSpecification<MO> implements Specification<MO> {

    @Serial
    private static final long serialVersionUID = -1039252242855458138L;

    protected final List<Predicate> predicates = new ArrayList<>();

    public void addPredicate(Predicate predicate) {
        predicates.add(predicate);
    }

    public Predicate buildPredicate(CriteriaBuilder cb) {
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
