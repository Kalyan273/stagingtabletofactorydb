package com.factory.appraisal.factoryService.persistence.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


/**
 * This is a class used for auditing
 */


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class TransactionEntity extends IdEntity {

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;


    @Column(name = "created_on")
    @CreatedDate
    //@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date createdOn;


    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

    @Column(name = "modified_on")
    @LastModifiedDate
    //@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date modifiedOn;


}
