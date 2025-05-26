/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prueba.application.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.*;

@Entity
@Table(name = "PRICES")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PriceMO implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "START_DATE")
  private OffsetDateTime startDate;

  @Column(name = "END_DATE")
  private OffsetDateTime endDate;

  @Column(name = "BRAND_ID")
  private Long brandId;

  @Column(name = "PRICE_LIST")
  private Long priceList;

  @Column(name = "PRODUCT_ID")
  private Long productId;

  @Column(name = "PRIORITY")
  private Long priority;

  @Column(name = "PRICE")
  private Double price;

  @Column(name = "CURR")
  private String currency;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "BRAND_ID", referencedColumnName = "ID", insertable = false, updatable = false)
  private BrandMO brandMO;
}
