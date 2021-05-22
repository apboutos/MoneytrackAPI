package com.apboutos.moneytrackapi.controller.exception;

import lombok.Getter;

public class CategoryExistsException extends Exception{

    @Getter
    private final String message;

    public CategoryExistsException(){
        message = null;
    }
    public CategoryExistsException(String message){
        super(message);
        this.message = message;
    }

}
