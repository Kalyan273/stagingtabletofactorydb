package com.factory.appraisal.factoryService.dto;

import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Shipment {

    private Boolean buyerAgreed=false;
    private Boolean sellerAgreed=false;
    private String buyerSign;
    private String sellerSign;
    private UUID buyerUserId;
    private UUID sellerUserId;

}
