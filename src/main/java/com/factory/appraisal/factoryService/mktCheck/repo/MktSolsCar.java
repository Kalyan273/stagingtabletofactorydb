package com.factory.appraisal.factoryService.mktCheck.repo;

import com.factory.appraisal.factoryService.mktCheck.model.EMkSoldCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface MktSolsCar extends JpaRepository<EMkSoldCar,Long> {


}
