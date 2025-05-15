package com.bcnc.prueba.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    private Long productId;
    private Long brandId;
    private Long priceList;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private String price;
}
