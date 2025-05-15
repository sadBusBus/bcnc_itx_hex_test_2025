package com.bcnc.prueba.adapter;

import com.bcnc.prueba.application.adapter.PriceDbAdapter;
import com.bcnc.prueba.application.mapper.PriceDatabaseMapper;
import com.bcnc.prueba.application.model.PriceMO;
import com.bcnc.prueba.application.repository.PriceDbRepository;
import com.bcnc.prueba.application.specification.impl.PriceSpecification;
import com.bcnc.prueba.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceDbAdapterTest {

    @InjectMocks
    private PriceDbAdapter priceDbAdapter;

    @Mock
    private PriceDbRepository repository;

    @Mock
    private PriceDatabaseMapper priceDbMapper;

    private static final Long PRODUCT_ID = 35455L;
    private static final Long BRAND_ID = 1L;

    @Test
    public void testGetPrice_verifyRepositoryCall() {
        // Arrange
        PriceSpecification specification = PriceSpecification.of(null, PRODUCT_ID, BRAND_ID);

        when(repository.findOne(any(Specification.class))).thenReturn(Optional.of(PriceMO.builder().price(23D).currency("EUR").build()));
        when(priceDbMapper.toDomain(any(PriceMO.class))).thenReturn(Price.builder().build());

        // Act
        priceDbAdapter.getPrice(null, PRODUCT_ID, BRAND_ID);

    }

}
