package com.factory.appraisal.factoryService.mktCheck.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * This is an Entity class  EDealerRegistration
 */


@Entity
@Table(name = "MKT_DEALER_REG",schema = "marketcheck")
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class EMkDealerRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mkDealerId;
    private String sellerName;
    private String inventoryUrl;
    private String dataSource;
    private String status;
    private String listingCount;
    private String dealerType;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zip;
    private String latitude;
    private String longitude;
    private String sellerPhone;
    private String sellerEmail;
    private String userUuid;


}
