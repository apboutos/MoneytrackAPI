package com.apboutos.moneytrackapi.controller.exception;

import lombok.Getter;

@SuppressWarnings("unused")
public class TokenNotFoundException extends Exception{

    @Getter
    private final String message;

    public TokenNotFoundException(){
        this.message = null;
    }

    public TokenNotFoundException(String message){
        super(message);
        this.message = message;
    }
}
