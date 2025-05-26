/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prueba.application.mappers;

import com.bcnc.prueba.application.projections.PriceProjection;
import com.bcnc.prueba.domain.model.Price;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PriceDbMapper {
  Price toDomain(PriceProjection priceProjection);
}
