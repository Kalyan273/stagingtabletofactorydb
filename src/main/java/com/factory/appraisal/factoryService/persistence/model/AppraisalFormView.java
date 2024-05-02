package com.factory.appraisal.factoryService.persistence.model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "appraisalform_view")
@Immutable
@Data
public class AppraisalFormView {
    @Id
    private Long  apprRefId;
    private Integer vehicleYear;
    private String vehicleMake;
    private String vehicleModel;
    private String vehicleMileage;
    private String vinNumber;
    private String vehExtColor;
    private Long userId;
    private Long dealerId;
    private String role;
    private Date createdOn;
    private Date modifiedOn;
}
