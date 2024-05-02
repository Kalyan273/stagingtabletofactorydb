package com.factory.appraisal.factoryService.persistence.model;
import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * This Entity class EOffers
 */

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
@Entity
@Table(name = "OFFERS")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@DynamicInsert
@AttributeOverride(name = "id", column = @Column(name = "OFFER_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class EOffers extends TransactionEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFFERS_SEQ")
    @GenericGenerator(name = "OFFERS_SEQ", strategy=AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EAppraiseVehicle.class, fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "APPR_REF_ID", nullable = false)
    @Where(clause = "IS_ACTIVE=true")
    private EAppraiseVehicle appRef;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToMany(mappedBy = "offers", cascade = CascadeType.ALL)
    private List<EOfferQuotes> offerQuotes;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EStatus.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS_ID", nullable = false)
    @Where(clause = "IS_ACTIVE=true")
    private EStatus status;

    private Double price;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EUserRegistration.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SLR_USER_ID",nullable = false,updatable = false)
    @Where(clause = "IS_ACTIVE = true")
    private EUserRegistration sellerUserId;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EDealerRegistration.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SLR_DLR_ID",nullable = false,updatable = false)
    @Where(clause = "IS_ACTIVE = true")
    private EDealerRegistration sellerDealerId;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EUserRegistration.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "BUYER_USER_ID",nullable = false,updatable = false)
    @Where(clause = "IS_ACTIVE = true")
    private EUserRegistration buyerUserId;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EDealerRegistration.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "BUYER_DLR_ID",nullable = false,updatable = false)
    @Where(clause = "IS_ACTIVE = true")
    private EDealerRegistration buyerDealerId;

    private Integer makeNewOffer;
    private Boolean isTradeBuy=false;

    private Boolean buyerAccept=false;
    private Boolean buyerCounter=false;
    private Boolean buyerDecline;
    @Column(name="SLR_ACCEPT")
    private Boolean sellerAccept;
    @Column(name="SLR_COUNTER")
    private Boolean sellerCounter;
    @Column(name="SLR_DECLINE")
    private Boolean sellerDecline;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(mappedBy = "offers",cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private EShipment shipment;

}
