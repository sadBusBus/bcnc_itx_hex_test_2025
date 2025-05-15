package com.bcnc.prueba.application.mapper;

import com.bcnc.model.PriceDTO;
import com.bcnc.prueba.domain.model.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    PriceDTO toDto(Price price);
}
