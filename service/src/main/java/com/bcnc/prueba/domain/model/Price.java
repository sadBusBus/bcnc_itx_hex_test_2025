/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prueba.domain.model;

import java.time.OffsetDateTime;
import lombok.*;

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
