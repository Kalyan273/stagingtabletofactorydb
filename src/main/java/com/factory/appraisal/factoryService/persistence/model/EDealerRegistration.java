package com.factory.appraisal.factoryService.persistence.model;
// authorName:YogeshKumarV


import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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

/**
 * This is an Entity class  EDealerRegistration
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
@Table(name = "DEALER_REG",schema = "factory_db")
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "DEALER_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class EDealerRegistration extends TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEALER_REG_SEQ")
    @GenericGenerator(name = "DEALER_REG_SEQ", strategy=AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;
//    private Long mkDealerId;
    private String name;
    private String firstName;
    private String lastName;
    @Column(name ="APT_NUMBER")
    private String aptmentNumber;
    private String city;
    private String country;
    private String email;
    private String password;
    private String phoneNumber;
    @Column(name = "PROFILE_PIC")
    private String profilePicture;
    private String state;
    private String streetAddress;
    private String zipCode;
    private String latitude;
    private String longitude;
    @Column(name = "TAX_CERT")
    private String taxCertificate;
    @Column(name = "DS_NAME", unique = true)
    private String dealershipNames;
    @Column(name = "DS_ADDR")
    private String dealershipAddress;
    @Column(name = "DS_STREET")
    private String dealershipStreet;
    @Column(name = "DS_CITY")
    private String dealershipCity;
    @Column(name = "DS_ZIP_CODE")
    private String dealershipZipCode;
    @Column(name = "DS_PHONE_NO")
    private String dealershipPhNum;
    @Column(name = "DS_PIC")
    private String dealerPic;
    @Column(name = "DS_LIC")
    private Boolean dealerLicense;
    @Column(name = "CORP_NAME")
    private String corporationName;
    private String dealerCert;
    private String status;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(targetEntity = ECompany.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "COMP_ID",nullable = false)
    @Where(clause = "IS_ACTIVE = true")
    private ECompany company;





}
