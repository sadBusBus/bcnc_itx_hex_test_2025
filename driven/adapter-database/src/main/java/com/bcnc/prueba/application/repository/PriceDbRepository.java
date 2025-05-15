package com.bcnc.prueba.application.repository;

import com.bcnc.prueba.application.model.PriceMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceDbRepository extends JpaRepository<PriceMO, Long>, JpaSpecificationExecutor<PriceMO> {
}
