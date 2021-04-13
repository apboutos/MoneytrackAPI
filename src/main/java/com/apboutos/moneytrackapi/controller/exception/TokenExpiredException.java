package com.apboutos.moneytrackapi.controller.exception;

import lombok.Getter;

@SuppressWarnings("unused")
public class TokenExpiredException extends Exception{
    @Getter
    private final String message;

    public TokenExpiredException(){
        this.message = null;
    }
    public TokenExpiredException(String message){
        super(message);
        this.message = message;
    }
}
