package com.factory.appraisal.factoryService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MktInventory {
    @JsonProperty("num_found")
    private Integer noOfInv;
    private List<Object> listings;

}
