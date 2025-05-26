/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prueba.application.repository;

import com.bcnc.prueba.application.model.PriceMO;
import com.bcnc.prueba.application.projections.PriceProjection;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceDbRepository
    extends JpaRepository<PriceMO, Long>, JpaSpecificationExecutor<PriceMO> {

  @Query(
      "SELECT new com.bcnc.prueba.application.projections.PriceProjection("
          + "p.productId, "
          + "p.brandId, "
          + "p.priceList, "
          + "p.startDate, "
          + "p.endDate, "
          + "CONCAT(p.price, ' ', p.currency), "
          + "b.name) "
          + "FROM PriceMO p "
          + "JOIN p.brandMO b "
          + "WHERE p.brandId = :brandId "
          + "AND p.productId = :productId "
          + "AND :dateTime BETWEEN p.startDate AND p.endDate "
          + "ORDER BY p.priority DESC "
          + "LIMIT 1")
  Optional<PriceProjection> findPriceByDateProductAndBrand(
      @Param("dateTime") OffsetDateTime dateTime,
      @Param("productId") Long productId,
      @Param("brandId") Long brandId);
}
