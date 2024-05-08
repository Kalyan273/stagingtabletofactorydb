package com.factory.appraisal.factoryService.mktCheck.model;

import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import com.factory.appraisal.factoryService.persistence.model.IdEntity;
import com.factory.appraisal.factoryService.persistence.model.TransactionEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;

import javax.persistence.*;


@Entity
@Table(name = "mkt_active_inventory",schema = "marketcheck")
@AuditOverrides({
        @AuditOverride(forClass= TransactionEntity.class, name="createdBy"),
        @AuditOverride(forClass=TransactionEntity.class, name="createdOn"),
        @AuditOverride(forClass=TransactionEntity.class, name="modifiedBy"),
        @AuditOverride(forClass=TransactionEntity.class, name="modifiedOn"),
        @AuditOverride(forClass= IdEntity.class, name="version"),
        @AuditOverride(forClass=IdEntity.class, name="sourceSystem"),
        @AuditOverride(forClass= IdEntity.class, name="valid")
})
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "mkt_inv_car_id"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class EInventoryVehicles extends TransactionEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mkt_inventory_id_seq")
    @GenericGenerator(name = "mkt_inventory_id_seq", strategy= AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;

    //vinInfo
    private String vin;
    private String heading;
    private Integer price;
    private Integer refPrice;
    private Integer miles;
    private Integer msrp;
    private String exteriorColor;
    private String  interiorColor;
    private String  baseIntColor;
    private String baseExtColor;
    private String sellerType;
    private String inventoryType;
    private String source;
    private Boolean inTransit;
    private String availabilityStatus;

    //dealer this mkDealerID
    @Column(name = "mk_dealer_id")
    private String dealerId;

    //build
    private Integer year;
    private String make;
    private String model;
    private String trim;
    private String bodyType;
    private String vehicleType;
    private String transmission;
    private String drivetrain;
    private String fuelType;
    private String engine;
    private String doors;
    private String madeIn;
    private String overallHeight;
    private String overallLength;
    private String overallWidth;
    private String stdSeating;
    private String highwayMpg;
    private String cityMpg;
    private String powertrainType;

    //media
    private String vehiclePic1;
    private String vehiclePic2;
    private String vehiclePic3;
    private String vehiclePic4;
    private String vehiclePic5;
    private String vehiclePic6;
    private String vehiclePic7;
    private String vehiclePic8;
    private String vehiclePic9;

}
