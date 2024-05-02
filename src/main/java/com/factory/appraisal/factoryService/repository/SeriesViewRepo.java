package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.SeriesView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesViewRepo extends JpaRepository<SeriesView,String > {
}
