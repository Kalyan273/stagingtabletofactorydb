package com.factory.appraisal.factoryService.dto;


import com.factory.appraisal.factoryService.ExceptionHandle.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserExistResponse extends Response {
    public List<UserResponse> response;
}
