package com.factory.appraisal.factoryService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MktDealer {
     @JsonProperty("num_found")
     private Integer noOfDealers;
     private List<Object> dealers;
}
