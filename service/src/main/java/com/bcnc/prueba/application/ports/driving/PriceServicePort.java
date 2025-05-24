package com.bcnc.prueba.application.ports.driving;

import com.bcnc.prueba.domain.model.Price;

import java.time.OffsetDateTime;

public interface PriceServicePort {
    Price findPriceByDateProductAndBrand(OffsetDateTime dateTime, Long productId, Long brandId);
}
