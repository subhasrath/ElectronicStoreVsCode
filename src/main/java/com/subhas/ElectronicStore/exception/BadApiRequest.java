package com.subhas.ElectronicStore.exception;

import lombok.Builder;

/**
 * BadApiRequest
 */
@Builder 
public class BadApiRequest extends RuntimeException{
     public BadApiRequest(){
        super("Bad Request");
    }

    public BadApiRequest(String message){
        super(message);
    }


}
