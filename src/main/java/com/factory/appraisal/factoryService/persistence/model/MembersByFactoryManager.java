package com.factory.appraisal.factoryService.persistence.model;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Immutable
@Data
public class MembersByFactoryManager {
    @Id
    private Long userId ;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private Date signFrom;
    private Double amount;
    private Long factoryManager;
    @Column(name = "company_name")
    private String compName;
    private String fmFirstName;
    private String  fmLastName;
}
