package com.factory.appraisal.factoryService.dto;

/**
 *
 * This is the Model class use for to send the data to UI
 *
 * @author Rupesh Khade
 */

import com.factory.appraisal.factoryService.persistence.model.EConfigCodes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


/**
 * This class is a DTO which will give show the provided fields in card
 */

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
@ApiModel(description = "Appraisal card details")
public class AppraisalVehicleCard {


    private Long id;
    @ApiModelProperty(notes = "Manufacturer name")
    private String vehicleMake ;
    private String  vehicleModel ;
    private double appraisedValue;
    private String createdBy;
    private Long vehicleYear;
    private Long vehicleMileage;
    private String  vehicleSeries;
    private String offerStatus;
    private Boolean isVehicleFav = Boolean.FALSE;
    private String createdOn;
    private String modifiedOn;
    private Long apprRef;
    private Long offerId;
    private Long color;
    private Long statusCodeId;
    private String soldSts;
    private String vehiclePic1;
    private String vinNumber;
    private String style;
    private String invntrySts;
    private Boolean isHold = Boolean.FALSE;
    private Boolean field1 =Boolean.FALSE;
    private Boolean field2=Boolean.FALSE;
    private Boolean isOfferMade= Boolean.FALSE;
    private ConfigDropDown titleSts;
    private Status status;
    private Boolean isSold= Boolean.FALSE;

    private Long shipmentId;
    private Boolean buyerAgreed= Boolean.FALSE;
    private Boolean sellerAgreed= Boolean.FALSE;
    private Role role;     //newly added
}
