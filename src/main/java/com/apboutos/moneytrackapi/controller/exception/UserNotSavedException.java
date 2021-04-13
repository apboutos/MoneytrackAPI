package com.apboutos.moneytrackapi.controller.exception;

import lombok.Getter;

@SuppressWarnings("unused")
public class UserNotSavedException extends Exception{

    @Getter
    private final String message;

    public UserNotSavedException(){
        this.message = null;
    }

    public UserNotSavedException(String message){
        super(message);
        this.message = message;
    }
}
