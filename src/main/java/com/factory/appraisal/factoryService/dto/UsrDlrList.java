package com.factory.appraisal.factoryService.dto;


import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UsrDlrList extends Response {
    List<UserRegistration> userList;
    public UserRegistration details;

}
