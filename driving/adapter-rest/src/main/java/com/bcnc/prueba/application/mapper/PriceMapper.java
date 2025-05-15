package com.bcnc.prueba.application.mapper;

import com.bcnc.prueba.application.model.price.PriceDTO;
import com.bcnc.prueba.domain.model.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    PriceDTO toDto(Price price);
}
