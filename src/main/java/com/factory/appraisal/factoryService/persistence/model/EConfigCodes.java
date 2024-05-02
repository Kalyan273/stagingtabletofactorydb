package com.factory.appraisal.factoryService.persistence.model;
/**
 * @author : YogeshKumarV,Rupesh Khade
 */


import com.factory.appraisal.factoryService.constants.AppraisalConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * This is an Entity class  EConfigurationCodes
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
@Table(name = "CONFIG_CODES")
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "CODE_ID"))
@AttributeOverride(name = "valid", column = @Column(name = "IS_ACTIVE"))
public class EConfigCodes extends TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONFIG_CODES_SEQ")
    @GenericGenerator(name = "CONFIG_CODES_SEQ", strategy=AppraisalConstants.CUSTOM_SEQUENCE_GENERATOR)
    private Long id;

    private String codeType;
    private String shortCode;
    private String longCode;
    @Column(name = "SHORT_DESC")
    private String shortDescrip;
    @Column(name = "LONG_DESC")
    private String longDescrip;
    @Column(name = "CODE_GROUP")
    private String configGroup;
    @Column(name = "INT_VALUE")
    private Integer intValue;
    public EConfigCodes(String codeType, String shortCode, String longCode, String shortDescription,String LongDescrip, String configGroup) {

        this.codeType = codeType;
        this.shortCode = shortCode;
        this.longCode = longCode;
        this.shortDescrip = shortDescription;
        this.longDescrip=LongDescrip;
        this.configGroup=configGroup;
        this.intValue=intValue;
    }
}