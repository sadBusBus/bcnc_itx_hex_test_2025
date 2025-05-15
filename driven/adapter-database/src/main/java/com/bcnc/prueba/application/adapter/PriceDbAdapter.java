package com.bcnc.prueba.application.adapter;

import com.bcnc.prueba.application.exception.NotFoundException;
import com.bcnc.prueba.application.mapper.PriceDatabaseMapper;
import com.bcnc.prueba.application.model.PriceMO;
import com.bcnc.prueba.application.ports.driven.PriceDbPort;
import com.bcnc.prueba.application.repository.PriceDbRepository;
import com.bcnc.prueba.application.specification.impl.PriceSpecification;
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
    private final PriceDatabaseMapper priceDbMapper;

    @Override
    public Price getPrice(OffsetDateTime dateTime, Long productId, Long brandId) {
        PriceSpecification specification = PriceSpecification.of(dateTime, productId, brandId);

        Optional<PriceMO> priceToReturn = repository.findOne(specification);

        return priceToReturn
                .map(priceDbMapper::toDomain)
                .orElseThrow(() -> new NotFoundException(PRICE_NOT_FOUND.name(), List.of(), PRICE_NOT_FOUND.getCode()));
    }
}
