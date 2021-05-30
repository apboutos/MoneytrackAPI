package com.apboutos.moneytrackapi.controller.exception;

import lombok.Getter;

public class CategoryNotFoundException extends Exception{

    @Getter
    private final String message;

    public CategoryNotFoundException(){
        message = null;
    }
    public CategoryNotFoundException(String message){
        super(message);
        this.message = message;
    }
}
