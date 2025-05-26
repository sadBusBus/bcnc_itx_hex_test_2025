/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prueba.application.adapter;

import com.bcnc.api.PriceApi;
import com.bcnc.model.PriceDTO;
import com.bcnc.prueba.application.mapper.PriceMapper;
import com.bcnc.prueba.application.ports.driving.PriceServicePort;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceController implements PriceApi {

  private final PriceServicePort priceServicePort;
  private final PriceMapper priceMapper;

  @Override
  public ResponseEntity<PriceDTO> findPriceByDateProductAndBrand(
      OffsetDateTime dateTime, Long productId, Long brandId) {
    return ResponseEntity.ok(
        priceMapper.toDto(
            priceServicePort.findPriceByDateProductAndBrand(dateTime, productId, brandId)));
  }
}
