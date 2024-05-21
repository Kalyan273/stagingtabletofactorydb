package com.factory.appraisal.factoryService.mktCheck.repo;

import com.factory.appraisal.factoryService.mktCheck.model.EMkSoldCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface MktSoldCarRepo extends JpaRepository<EMkSoldCar,Long> {

    @Query("select c.vin from EMkSoldCar c")
    List<String> getAllMktSoldCars();
    @Query("select count(*) from EMkSoldCar c")
    Long  getAllMktSoldCarsCount();


}
