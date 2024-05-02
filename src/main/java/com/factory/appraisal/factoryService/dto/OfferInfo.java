package com.factory.appraisal.factoryService.dto;

import com.factory.appraisal.factoryService.ExceptionHandle.Response;

import io.swagger.annotations.ApiModel;

import lombok.*;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
@ApiModel(description = "Offer card info")
public class OfferInfo extends Response {
    private AppraisalVehicleCard card;
    private Status statusInfo;
    List<Quotes> quotesList;
    private Long offerId;

}
