package com.factory.appraisal.factoryService.persistence.model;
//@author:Rupesh Khade

import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * This is an Entity class  EAppraiseVehicle
 */

@Entity
@Table(name = "APPRAISAL_VEHICLE")
@Audited
@AuditOverrides({
        @AuditOverride(forClass=TransactionEntity.class, name="createdBy", isAudited=true),
        @AuditOverride(forClass=TransactionEntity.class, name="createdOn", isAudited=true),
        @AuditOverride(forClass=TransactionEntity.class, name="modifiedBy", isAudited=true),
        @AuditOverride(forClass=TransactionEntity.class, name="modifiedOn", isAudited=true),
        @AuditOverride(forClass= IdEntity.class, name="version",isAudited=true),
        @AuditOverride(forClass=IdEntity.class, name="sourceSystem", isAudited=true),
        @AuditOverride(forClass= IdEntity.class, name="valid", isAudited=true)
})
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "APPR_REF_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class EAppraiseVehicle extends TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appr_veh_seq")
    @GenericGenerator(name = "appr_veh_seq", strategy=AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;


    @Column(name="APPR_RETENSION_TIME")
    private Date apprRetensionTime;

    @Column(name="APPR_VALUE")
    private Double appraisedValue;

    @Column(name="BB_VALUE")
    private Double blackBookValue;

    @Column(name = "DLR_RESERVE_VALUE")
    private Double dealerReserve ;

    @Column(name = "DLR_ASK_PRICE")
    private Double delrRetlAskPrice ;

    @Column(name = "CNSR_ASK_PRICE")
    private Double consumerAskPrice ;

    @Column(name = "CLT_FIRST_NAME")
    private String clientFirstName;

    @Column(name = "CLT_LAST_NAME")
    private String clientLastName;

    @Column(name = "CLT_PH_NUMBER")
    private String clientPhNum;

    @Column(name="FIELD1")
    private Boolean field1=Boolean.FALSE;

    @Column(name="FIELD2")
    private Boolean field2=Boolean.FALSE;

    @Column(name="INVENTORY_DATE")
    private Date invntryDate;

    @Column(name="PROF_OPINION")
    private String profOpinion ;

    @Column(name = "VEHICLE_DESC")
    private  String vehicleDesc ;

    @Column(name = "STOCK_NUMBER")
    private Long stockNumber;

    @Column(name="MAKE")
    private String vehicleMake;

    @Column(name="MILES")
    private Long vehicleMileage;

    @Column(name="MODEL")
    private String  vehicleModel ;

    @Column(name="SERIES")
    private String  vehicleSeries;

    @Column(name="VEH_YEAR")
    private Long vehicleYear;

    @Column(name="VIN_NUMBER ")
    private String vinNumber;

    @Column(name = "INV_STATUS")
    private String invntrySts;

    @Column(name = "IS_OFFER_MADE")
    private Boolean isOfferMade=false;

    @Column(name = "IS_HOLD")
    private Boolean isHold=false;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EUserRegistration.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DLRS_USER_NAMES")
    @Where(clause = "IS_ACTIVE = true")
    private EUserRegistration dlrsUserNames;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EDealerRegistration.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DEALER_ID")
    @Where(clause = "IS_ACTIVE = true")
    private EDealerRegistration dealer;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = EUserRegistration.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID",nullable = false,updatable = false)
    @Where(clause = "IS_ACTIVE = true")
    private EUserRegistration user;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "appraisalRef",cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private EApprTestDrSts tdStatus;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToMany(mappedBy = "apprRef",cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private List<OBD2_PreStartMeasurement> preStart;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToMany(mappedBy = "apprRef",cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private List<OBD2_TestDriveMeasurements> testDriveMeas;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "apprRef",cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private ESignDet signDet;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToMany(mappedBy = "appRef",cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private List<EUserWishlist> wishlist;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToMany(mappedBy = "appRef",cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private List<EOffers> offers;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "appraisalRef",cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private EShipment shipment;

//    private String fromMkt;
//
}
