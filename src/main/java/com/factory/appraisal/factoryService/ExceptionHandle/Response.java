package com.factory.appraisal.factoryService.ExceptionHandle;

import lombok.*;

/**
 * This class is used as return type for every API
 */

@Getter
@Setter
@NoArgsConstructor

public class Response {

    private Integer code;
    private String message;
    private Boolean status;
    private Long apprId;


    public Response(int value, String roleCreated, boolean status) {
       this.code=value;
       this.message=roleCreated;
       this.status=status;
    }
}
