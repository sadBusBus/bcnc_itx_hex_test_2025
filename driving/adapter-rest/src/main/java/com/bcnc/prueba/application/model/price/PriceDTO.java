package com.bcnc.prueba.application.model.price;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceDTO {
    private Long productId;
    private Long brandId;
    private Long priceList;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private String price;
}
