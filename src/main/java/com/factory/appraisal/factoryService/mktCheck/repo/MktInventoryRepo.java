package com.factory.appraisal.factoryService.mktCheck.repo;


import com.factory.appraisal.factoryService.mktCheck.model.EInventoryVehicles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MktInventoryRepo extends JpaRepository<EInventoryVehicles,Long> {
   List<EInventoryVehicles> findByDealerId(String mkDealerId);


    void findByVin(String invInfo);

    @Query(value = "select e from EInventoryVehicles e ORDER BY e.vin DESC")
    Page<EInventoryVehicles> getAllInv(Pageable pageable);
}
