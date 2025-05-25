package com.bcnc.prueba.application.specification.adapter;

import com.bcnc.prueba.application.adapter.PriceDbAdapter;
import com.bcnc.prueba.application.mappers.PriceDbMapper;
import com.bcnc.prueba.application.projections.PriceProjection;
import com.bcnc.prueba.application.repository.PriceDbRepository;
import com.bcnc.prueba.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceDbAdapterTest {

    @InjectMocks
    private PriceDbAdapter priceDbAdapter;

    @Mock
    private PriceDbRepository repository;

    @Mock
    private PriceDbMapper priceDbMapper;

    private static final Long PRODUCT_ID = 35455L;
    private static final Long BRAND_ID = 1L;

    @Test
     void shouldReturnPriceWhenFoundInRepository() {
        // Arrange
        when(repository.findPriceByDateProductAndBrand(any(),any(),any())).thenReturn(Optional.of(PriceProjection.builder().price("2 eur").build()));
        when(priceDbMapper.toDomain(any())).thenReturn(Price.builder().price("2 eur").build());

        // Act
        Price result = priceDbAdapter.findPriceByDateProductAndBrand(null, PRODUCT_ID, BRAND_ID);

        // Assert
        assertSame("2 eur", result.getPrice());
    }

}
