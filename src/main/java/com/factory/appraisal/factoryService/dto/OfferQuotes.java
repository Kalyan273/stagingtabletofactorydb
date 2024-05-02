package com.factory.appraisal.factoryService.dto;



import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class OfferQuotes {

    private AppraiseVehicle appRef;
    private Offers offers;
    @DecimalMin(value = "0", inclusive = false)
    @DecimalMax(value = "9999999999999.99",message = "value must be less than 13 digits")
    private Double sellerQuote;
    @DecimalMin(value = "0", inclusive = false)
    @DecimalMax(value = "9999999999999.99",message = "value must be less than 13 digits")
    private Double buyerQuote;
    private String offerStatus;

}
