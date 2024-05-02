package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.UserListView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserListViewRepo extends JpaRepository<UserListView,Long>, JpaSpecificationExecutor<UserListView> {
}
