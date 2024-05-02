package com.factory.appraisal.factoryService.persistence.model;
//@Author: Yudhister vijay

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
import java.util.List;
import java.util.UUID;

/**
 * This is an Entity class  EUserRegistration
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
@Table(name = "USER_REG",schema = "factory_db")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
@AttributeOverride(name = "id", column = @Column(name = "USER_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class EUserRegistration extends TransactionEntity {

/*    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_REG_SEQ")
    @GenericGenerator(name = "USER_REG_SEQ", strategy=AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)*/
//    private Long id;
    @Id
    private UUID id;

    @Column(name = "APT_NUMBER")
    private String apartmentNumber;
    private String city;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "state")
    private String state;
    @Column(name = "street_address")
    private String streetAddress;
    @Column(unique = true,name = "user_name")
    private String userName;
    @Column(name = "zip_code")
    private String zipCode;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(targetEntity = EDealerRegistration.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DEALER_ID",nullable = true)
    @Where(clause = "IS_ACTIVE=true")
    private EDealerRegistration dealer;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @Where(clause = "IS_ACTIVE = true")
    private List<ERoleMapping> roleMapping;

}