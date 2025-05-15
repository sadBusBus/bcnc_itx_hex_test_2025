package com.bcnc.prueba.domain.model;

import lombok.*;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Price {
    private Long productId;
    private Long brandId;
    private Long priceList;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private String price;
    private String brandName;
}
