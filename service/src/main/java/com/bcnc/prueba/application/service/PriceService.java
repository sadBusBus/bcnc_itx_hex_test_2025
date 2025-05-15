package com.bcnc.prueba.application.service;

import com.bcnc.prueba.application.ports.driven.PriceDbPort;
import com.bcnc.prueba.application.ports.driving.PriceServicePort;
import com.bcnc.prueba.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class PriceService implements PriceServicePort {

    private final PriceDbPort priceDbPort;

    @Override
    @Transactional
    public Price getPrice(OffsetDateTime dateTime, Long productId, Long brandId) {
        return priceDbPort.getPrice(dateTime, productId, brandId);
    }
}
