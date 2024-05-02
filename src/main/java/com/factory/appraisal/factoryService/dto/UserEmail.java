package com.factory.appraisal.factoryService.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserEmail {
    private String type;
    private String value;
    private Boolean primary;
}
