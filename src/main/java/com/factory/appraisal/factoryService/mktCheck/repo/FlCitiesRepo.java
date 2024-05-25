package com.factory.appraisal.factoryService.mktCheck.repo;

import com.factory.appraisal.factoryService.mktCheck.model.ECities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface FlCitiesRepo extends JpaRepository<ECities,Long> {


    @Query(value = "select e.name from ECities e  where e.valid=true order by e.name")
    List<String> getCityNames();
}
