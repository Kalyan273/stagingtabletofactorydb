package com.factory.appraisal.factoryService.mktCheck.model;

import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mkt_dealer_reg_id_seq")
    @GenericGenerator(name = "mkt_dealer_reg_id_seq", strategy= AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
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
