package com.factory.appraisal.factoryService.ExceptionHandle;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OfferException extends Exception{


    public OfferException(String message){
        super(message);
    }
}
