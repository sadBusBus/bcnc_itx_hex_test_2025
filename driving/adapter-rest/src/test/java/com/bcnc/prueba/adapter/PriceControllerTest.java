package com.bcnc.prueba.adapter;

import com.bcnc.model.PriceDTO;
import com.bcnc.prueba.application.adapter.PriceController;
import com.bcnc.prueba.application.mapper.PriceMapper;
import com.bcnc.prueba.application.ports.driving.PriceServicePort;
import com.bcnc.prueba.domain.model.Price;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import java.time.OffsetDateTime;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PriceControllerTest {

    @InjectMocks
    private PriceController priceController;

    @Mock
    private PriceServicePort priceServicePort;

    @Mock
    private PriceMapper priceMapper;

    private static final Long PRODUCT_ID = 35455L;
    private static final Long BRAND_ID = 1L;

    @ParameterizedTest
    @MethodSource("providePriceTestCases")
    void testGetPrice(String dateTime, Long expectedPriceList, String expectedPrice, String startDate, String endDate) {
        // Arrange
        OffsetDateTime requestDateTime = OffsetDateTime.parse(dateTime);
        Price price = createPrice(expectedPriceList, expectedPrice, startDate, endDate);
        PriceDTO priceDTO = createPriceDTO(expectedPriceList, expectedPrice, startDate, endDate);

        when(priceServicePort.getPrice(requestDateTime, PRODUCT_ID, BRAND_ID)).thenReturn(price);
        when(priceMapper.toDto(price)).thenReturn(priceDTO);

        // Act
        PriceDTO result = priceController.getPrice(requestDateTime, PRODUCT_ID, BRAND_ID).getBody();

        // Assert
        assertEquals(PRODUCT_ID, result.getProductId());
        assertEquals(BRAND_ID, result.getBrandId());
        assertEquals(expectedPriceList, result.getPriceList());
        assertEquals(expectedPrice, result.getPrice());

        // Verify
        verify(priceServicePort, times(1)).getPrice(requestDateTime, PRODUCT_ID, BRAND_ID);
        verify(priceMapper, times(1)).toDto(price);
    }

    private static Stream<Arguments> providePriceTestCases() {
        return Stream.of(
            // Test 1: 2025-06-14 10:00
            Arguments.of("2025-06-14T10:00:00Z", 1L, "35.50 EUR", "2025-06-14T00:00:00Z", "2025-06-14T12:00:00Z"),
            // Test 2: 2025-06-14 16:00
            Arguments.of("2025-06-14T16:00:00Z", 2L, "25.45 EUR", "2025-06-14T15:00:00Z", "2025-06-14T18:00:00Z"),
            // Test 3: 2025-06-14 21:00
            Arguments.of("2025-06-14T21:00:00Z", 1L, "35.50 EUR", "2025-06-14T18:01:00Z", "2025-06-15T00:00:00Z"),
            // Test 4: 2025-06-15 10:00
            Arguments.of("2025-06-15T10:00:00Z", 3L, "30.50 EUR", "2025-06-15T00:00:00Z", "2025-06-15T12:00:00Z"),
            // Test 5: 2025-06-16 21:00
            Arguments.of("2025-06-16T21:00:00Z", 4L, "38.95 EUR", "2025-06-16T18:00:00Z", "2025-06-17T00:00:00Z")
        );
    }

    private Price createPrice(Long priceList, String price, String startDate, String endDate) {
        Price p = new Price();
        p.setProductId(PRODUCT_ID);
        p.setBrandId(BRAND_ID);
        p.setPriceList(priceList);
        p.setPrice(price);
        p.setStartDate(OffsetDateTime.parse(startDate));
        p.setEndDate(OffsetDateTime.parse(endDate));
        return p;
    }

    private PriceDTO createPriceDTO(Long priceList, String price, String startDate, String endDate) {
        PriceDTO dto = new PriceDTO();
        dto.setProductId(PRODUCT_ID);
        dto.setBrandId(BRAND_ID);
        dto.setPriceList(priceList);
        dto.setPrice(price);
        dto.setStartDate(OffsetDateTime.parse(startDate));
        dto.setEndDate(OffsetDateTime.parse(endDate));
        return dto;
    }
}
