package com.bcnc.prueba.application.adapter;

import com.bcnc.prueba.application.exception.NotFoundException;
import com.bcnc.prueba.application.ports.driven.PriceDbPort;
import com.bcnc.prueba.application.repository.PriceDbRepository;
import com.bcnc.prueba.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static com.bcnc.prueba.application.exception.ErrorCodes.PRICE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PriceDbAdapter implements PriceDbPort {
    private final PriceDbRepository repository;

    @Override
    public Price findPriceByDateProductAndBrand(OffsetDateTime dateTime, Long productId, Long brandId) {

        Optional<Price> priceToReturn = repository.findPriceByDateProductAndBrand(dateTime,productId,brandId);

        return priceToReturn
            .orElseThrow(() -> new NotFoundException(PRICE_NOT_FOUND.name(), List.of(), PRICE_NOT_FOUND.getCode()));
    }
}
