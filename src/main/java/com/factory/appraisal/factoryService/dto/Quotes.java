package com.factory.appraisal.factoryService.dto;

import com.factory.appraisal.factoryService.persistence.model.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Quotes {
    private Double BuyerQuote;
    private Double SellerQuote;
    private Double appraisedValue;
    private String status;

}
