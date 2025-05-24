package com.bcnc.prueba.application.ports.driven;

import com.bcnc.prueba.domain.model.Price;

import java.time.OffsetDateTime;

public interface PriceDbPort {
    Price findPriceByDateProductAndBrand(OffsetDateTime dateTime, Long productId, Long brandId);
}
