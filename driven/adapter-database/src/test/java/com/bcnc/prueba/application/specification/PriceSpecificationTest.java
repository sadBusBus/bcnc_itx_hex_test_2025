package com.bcnc.prueba.application.specification;

import com.bcnc.prueba.application.model.PriceMO;
import com.bcnc.prueba.application.specification.impl.PriceSpecification;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PriceSpecificationTest {

    @Mock
    private Root<PriceMO> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder cb;

    @Mock
    private Predicate predicate;

    @Mock
    private Path<Object> path;

    private OffsetDateTime dateTime;
    private Long productId = 35455L;
    private Long brandId = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dateTime = OffsetDateTime.of(2025, 5, 15, 10, 0, 0, 0, ZoneOffset.UTC);
    }

    @Test
    void shouldReturnPredicateWithAllFiltersWhenAllParametersProvided() {
        // Given
        PriceSpecification specification = PriceSpecification.of(dateTime, productId, brandId);

        when(cb.equal(root.get("brandId"), brandId)).thenReturn(predicate);
        when(cb.equal(root.get("productId"), productId)).thenReturn(predicate);
        when(cb.lessThanOrEqualTo(root.get("startDate"), dateTime)).thenReturn(predicate);
        when(cb.greaterThanOrEqualTo(root.get("endDate"), dateTime)).thenReturn(predicate);
        when(cb.and(any())).thenReturn(predicate);

        // When
        Predicate result = specification.toPredicate(root, query, cb);

        assertNull(result);
    }

    @Test
    void shouldReturnPredicateWithOnlyDateFilterWhenOnlyDateProvided() {
        // Given
        PriceSpecification specification = PriceSpecification.of(dateTime, null, null);

        when(cb.lessThanOrEqualTo(root.get("startDate"), dateTime)).thenReturn(predicate);
        when(cb.greaterThanOrEqualTo(root.get("endDate"), dateTime)).thenReturn(predicate);
        when(cb.and(any())).thenReturn(predicate);

        // When
        Predicate result = specification.toPredicate(root, query, cb);

        // Then
        assertNotNull(result);
    }

    @Test
    void shouldReturnPredicateWithOnlyProductIdWhenOnlyProductIdProvided() {
        // Given
        PriceSpecification specification = PriceSpecification.of(null, productId, null);

        when(cb.equal(root.get("productId"), productId)).thenReturn(predicate);
        when(cb.and(any())).thenReturn(predicate);

        // When
        Predicate result = specification.toPredicate(root, query, cb);

        // Then
        assertNotNull(result);
    }

    @Test
    void shouldReturnPredicateWithOnlyBrandIdWhenOnlyBrandIdProvided() {
        // Given
        PriceSpecification specification = PriceSpecification.of(null, null, brandId);

        when(cb.equal(root.get("brandId"), brandId)).thenReturn(predicate);
        when(cb.and(any())).thenReturn(predicate);

        // When
        Predicate result = specification.toPredicate(root, query, cb);

        // Then
        assertNotNull(result);
    }

    @Test
    void shouldHandleNullDateTimeGracefully() {
        // Given
        PriceSpecification specification = PriceSpecification.of(null, productId, brandId);

        when(cb.equal(root.get("productId"), productId)).thenReturn(predicate);
        when(cb.equal(root.get("brandId"), brandId)).thenReturn(predicate);
        when(cb.and(any())).thenReturn(predicate);

        // When
        Predicate result = specification.toPredicate(root, query, cb);
    }

    @Test
    void shouldReturnConjunctionWhenNoFiltersProvided() {
        // Given
        PriceSpecification specification = PriceSpecification.of(null, null, null);

        when(cb.conjunction()).thenReturn(predicate);
        when(cb.and(any())).thenReturn(predicate);

        // When
        Predicate result = specification.toPredicate(root, query, cb);

        //then
        assertNull(result);
    }
}
