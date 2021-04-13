package com.apboutos.moneytrackapi.controller.exception;

import lombok.Getter;

@SuppressWarnings("unused")
public class EmailTakenException extends Exception{

    @Getter
    private final String message;

    public EmailTakenException(){
        message = null;
    }
    public EmailTakenException(String message){
        super(message);
        this.message = message;
    }
}
