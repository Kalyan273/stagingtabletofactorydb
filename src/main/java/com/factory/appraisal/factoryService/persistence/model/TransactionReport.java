package com.factory.appraisal.factoryService.persistence.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name ="soldreports")
@Immutable
@NoArgsConstructor
@Getter
@Setter
public class TransactionReport {
    @Id
    private Long offerId;
    private Double price;
    private String vinNumber;
    private String vehYear;
    private String status;
    private String make;
    private String model;
    private Long miles;
    private String colour;
    private String firstName;
    private String lastName;
    private String userName;
    private Long userId;
    private Date createdOn;
    private String compName;



}
