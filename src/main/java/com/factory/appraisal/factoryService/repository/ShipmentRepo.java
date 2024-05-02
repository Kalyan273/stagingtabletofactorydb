package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.EShipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ShipmentRepo extends JpaRepository<EShipment,Long> {
    @Query(value = "select e from EShipment e where e.valid=true and e.id=:shipId and e.pushForBuyFig=false")
    EShipment findByShipId(Long shipId);


}
