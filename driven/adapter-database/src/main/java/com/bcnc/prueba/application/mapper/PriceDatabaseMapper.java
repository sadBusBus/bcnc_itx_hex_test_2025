package com.bcnc.prueba.application.mapper;

import com.bcnc.prueba.application.model.PriceMO;
import com.bcnc.prueba.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PriceDatabaseMapper {

    @Mapping(source = "brandMO.id", target = "brandId")
    @Mapping(source = "priceEntity", target = "price", qualifiedByName = "getPriceFormated")
    Price toDomain(PriceMO priceEntity);

    @Named("getPriceFormated")
    default String getPriceFormated(PriceMO priceEntity) {
        return priceEntity.getPrice() + priceEntity.getCurrency();
    }

}
