package com.factory.appraisal.factoryService.persistence.model;


import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Immutable
@Data
public class TotalMembersView {
    @Id
    private Long userId;
    private String memberFirstName;
    private String memberLastName;
    private String streetAddress;
    private String role;
    private Date signFrom;
    private String factorySalesmanFirstName;
    private String factorySalesmanLastName;
    private String factoryManagerFirstName;
    private String factoryManagerLastName;
    private Double amount;

}
