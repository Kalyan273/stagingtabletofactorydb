package com.factory.appraisal.factoryService.dto;



import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SellingDealer  {
    private String firstName;
    private String lastName;
    private Long userId;
    private Long dealerId;
    private String compName;
}
