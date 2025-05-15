package com.bcnc.prueba.adapter;


import com.bcnc.prueba.application.ports.driven.PriceDbPort;
import com.bcnc.prueba.application.service.PriceService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PriceServiceTest {

    @InjectMocks
    private PriceService priceService;

    @Mock
    private PriceDbPort priceDbPort;

    private static final Long PRODUCT_ID = 35455L;
    private static final Long BRAND_ID = 1L;

    @ParameterizedTest
    @MethodSource("providePriceTestCases")
    void testGetPriceVerifyDbPortCall(String dateTime) {
        // Arrange
        OffsetDateTime requestDateTime = OffsetDateTime.parse(dateTime);
        when(priceDbPort.getPrice(requestDateTime, PRODUCT_ID, BRAND_ID)).thenReturn(new Price());

        // Act
        priceService.getPrice(requestDateTime, PRODUCT_ID, BRAND_ID);

        // Assert
        verify(priceDbPort, times(1)).getPrice(requestDateTime, PRODUCT_ID, BRAND_ID);
    }

    private static Stream<Arguments> providePriceTestCases() {
        return Stream.of(
            Arguments.of("2025-06-14T10:00:00Z"),
            Arguments.of("2025-06-14T16:00:00Z"),
            Arguments.of("2025-06-14T21:00:00Z"),
            Arguments.of("2025-06-15T10:00:00Z"),
            Arguments.of("2025-06-16T21:00:00Z")
        );
    }
}
