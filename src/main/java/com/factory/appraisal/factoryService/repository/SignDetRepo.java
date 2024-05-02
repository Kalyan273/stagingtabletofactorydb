package com.factory.appraisal.factoryService.repository;
//Author:yudhister vijay
import com.factory.appraisal.factoryService.persistence.model.ESignDet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SignDetRepo extends JpaRepository<ESignDet,Long> {


}
