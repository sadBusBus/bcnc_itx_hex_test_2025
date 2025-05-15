package com.bcnc.prueba.application.adapter;

import com.bcnc.prueba.application.adapter.api.PriceAPI;
import com.bcnc.prueba.application.mapper.PriceMapper;
import com.bcnc.prueba.application.model.price.PriceDTO;
import com.bcnc.prueba.application.ports.driving.PriceServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
public class PriceController implements PriceAPI {

    private final PriceServicePort priceServicePort;
    private final PriceMapper priceMapper;

    @Override
    public ResponseEntity<PriceDTO> getPrice(OffsetDateTime dateTime, Long productId, Long brandId) {
        return ResponseEntity.ok(priceMapper.toDto(priceServicePort.getPrice(dateTime,productId,brandId)));
    }
}
