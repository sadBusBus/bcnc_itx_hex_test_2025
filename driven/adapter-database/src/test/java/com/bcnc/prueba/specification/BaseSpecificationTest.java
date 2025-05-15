package com.bcnc.prueba.specification;

import com.bcnc.prueba.application.specification.BaseSpecification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
    }

    @Test
    void shouldBuildPredicate() {
        // given
        given(cb.and(any())).willReturn(predicate);
        // when
        Predicate result = baseSpecification.buildPredicate(cb);
    }
}
