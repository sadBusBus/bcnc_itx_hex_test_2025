/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prueba.application.service;

import com.bcnc.prueba.application.ports.driven.PriceDbPort;
import com.bcnc.prueba.application.ports.driving.PriceServicePort;
import com.bcnc.prueba.domain.model.Price;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PriceService implements PriceServicePort {

  private final PriceDbPort priceDbPort;

  @Override
  @Transactional
  public Price findPriceByDateProductAndBrand(
      OffsetDateTime dateTime, Long productId, Long brandId) {
    return priceDbPort.findPriceByDateProductAndBrand(dateTime, productId, brandId);
  }
}
