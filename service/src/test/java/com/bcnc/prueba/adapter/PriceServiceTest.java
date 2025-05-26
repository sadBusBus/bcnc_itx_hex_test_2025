/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prueba.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.bcnc.prueba.application.ports.driven.PriceDbPort;
import com.bcnc.prueba.application.service.PriceService;
import com.bcnc.prueba.domain.model.Price;
import java.time.OffsetDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PriceServiceTest {

  @InjectMocks private PriceService priceService;

  @Mock private PriceDbPort priceDbPort;

  private static final Long PRODUCT_ID = 35455L;
  private static final Long BRAND_ID = 1L; // ZARA
  private static final String BRAND_NAME = "ZARA";

  @ParameterizedTest(name = "{0}")
  @MethodSource("providePriceTestCases")
  void shouldReturnCorrectPriceForSpecificDateAndProduct(
      String testDescription, String dateTime, Long expectedPriceList, String expectedPrice) {
    // Arrange
    OffsetDateTime requestDateTime = OffsetDateTime.parse(dateTime);
    Price mockPrice = createMockPrice(expectedPriceList, expectedPrice);
    when(priceDbPort.findPriceByDateProductAndBrand(requestDateTime, PRODUCT_ID, BRAND_ID))
        .thenReturn(mockPrice);

    // Act
    Price result =
        priceService.findPriceByDateProductAndBrand(requestDateTime, PRODUCT_ID, BRAND_ID);

    // Assert
    assertNotNull(result);
    assertEquals(PRODUCT_ID, result.getProductId());
    assertEquals(BRAND_ID, result.getBrandId());
    assertEquals(BRAND_NAME, result.getBrandName());
    assertEquals(expectedPriceList, result.getPriceList());
    assertEquals(expectedPrice, result.getPrice());
    verify(priceDbPort, times(1))
        .findPriceByDateProductAndBrand(requestDateTime, PRODUCT_ID, BRAND_ID);
  }

  private static Stream<Arguments> providePriceTestCases() {
    return Stream.of(
        Arguments.of(
            "Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)",
            "2025-06-14T10:00:00Z",
            "1",
            "35.50"),
        Arguments.of(
            "Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)",
            "2025-06-14T16:00:00Z",
            "2",
            "25.45"),
        Arguments.of(
            "Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)",
            "2025-06-14T21:00:00Z",
            "1",
            "35.50"),
        Arguments.of(
            "Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)",
            "2025-06-15T10:00:00Z",
            "3",
            "30.50"),
        Arguments.of(
            "Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)",
            "2025-06-16T21:00:00Z",
            "4",
            "38.95"));
  }

  private Price createMockPrice(Long priceList, String price) {
    OffsetDateTime startDate = OffsetDateTime.parse("2025-06-14T00:00:00Z");
    OffsetDateTime endDate = OffsetDateTime.parse("2025-06-17T00:00:00Z");

    return Price.builder()
        .productId(PRODUCT_ID)
        .brandId(BRAND_ID)
        .brandName(BRAND_NAME)
        .priceList(priceList)
        .startDate(startDate)
        .endDate(endDate)
        .price(price)
        .build();
  }
}
