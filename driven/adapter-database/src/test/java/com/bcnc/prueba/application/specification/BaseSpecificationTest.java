package com.bcnc.prueba.application.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public
class BaseSpecificationTest {

    private BaseSpecification baseSpecification = new BaseSpecification() {
        @Override
        public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
            return null;
        }

    };
    @Mock
    protected CriteriaBuilder cb;
    @Mock
    protected CriteriaQuery query;
    @Mock
    private Root<?> root;
    @Mock
    protected Predicate predicate;


    @Test
    void shouldAddPredicate() {
        // when
        baseSpecification.addPredicate(predicate);
        // then
        List<Predicate> predicateList = baseSpecification.predicates;
        assertEquals(1, predicateList.size());
    }

    @Test
    void shouldBuildPredicate() {
        // given
        given(cb.and(any())).willReturn(predicate);
        // when
        Predicate result = baseSpecification.buildPredicate(cb);
        // then
        assertNull(result);
    }

}
