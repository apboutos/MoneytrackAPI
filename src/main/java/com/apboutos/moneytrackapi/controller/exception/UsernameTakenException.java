package com.apboutos.moneytrackapi.controller.exception;

import lombok.Getter;

@SuppressWarnings("unused")
public class UsernameTakenException extends Exception{

    @Getter
    private final String message;

    public UsernameTakenException(){
        message = null;
    }
    public UsernameTakenException(String message){
        super(message);
        this.message = message;
    }
}
