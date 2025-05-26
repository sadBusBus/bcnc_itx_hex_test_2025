/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prueba.application.projections;

import java.time.OffsetDateTime;
import lombok.*;

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
