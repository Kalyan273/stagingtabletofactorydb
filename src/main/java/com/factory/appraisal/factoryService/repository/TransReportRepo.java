package com.factory.appraisal.factoryService.repository;

import com.factory.appraisal.factoryService.persistence.model.EAppraiseVehicle;
import com.factory.appraisal.factoryService.persistence.model.TransactionReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TransReportRepo extends JpaRepository<TransactionReport,Long>, JpaSpecificationExecutor<TransactionReport> {
    @Query("SELECT t FROM TransactionReport t WHERE t.userId = :id AND t.createdOn BETWEEN :start AND :end")
    List<TransactionReport>getTranList(Long id,Date start, Date end);

    @Query("select t from TransactionReport t WHERE t.createdOn BETWEEN :start AND :end")
    List<TransactionReport>getSoldList(Date start, Date end);

    @Query("select t from TransactionReport t")
    List<TransactionReport>getOfferList();

    @Query("SELECT t FROM TransactionReport t WHERE t.userId = :id AND t.createdOn BETWEEN :start AND :end")
    Page<TransactionReport> tranListTable(Long id,Pageable pageable, Date start, Date end);

    @Query("SELECT t FROM TransactionReport t WHERE t.createdOn BETWEEN :start AND :end")
    Page<TransactionReport> soldTable(Date start, Date end,Pageable pageable);



}
