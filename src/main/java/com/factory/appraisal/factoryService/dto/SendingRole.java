package com.factory.appraisal.factoryService.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SendingRole {
    @JsonProperty("Operations")
    List<Operation> operations;
}
