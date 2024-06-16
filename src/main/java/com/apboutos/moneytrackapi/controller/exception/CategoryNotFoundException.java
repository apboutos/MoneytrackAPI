package com.apboutos.moneytrackapi.controller.exception;

import lombok.Getter;

@Getter
public class CategoryNotFoundException extends Exception{

    private final String message;

    public CategoryNotFoundException(String message){
        super(message);
        this.message = message;
    }
}
