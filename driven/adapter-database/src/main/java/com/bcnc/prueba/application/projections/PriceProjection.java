package com.bcnc.prueba.application.projections;

import lombok.*;

import java.time.OffsetDateTime;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PriceProjection {
    private Long productId;
    private Long brandId;
    private Long priceList;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private String price;
    private String brandName;
}
