package com.factory.appraisal.factoryService.dto;
import lombok.*;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Users {
    private Name name;
    private String userName;
    private String password;
    private List<UserEmail> emails;

}
