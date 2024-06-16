package com.apboutos.moneytrackapi.controller.Response;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public record UserAuthenticationResponse(
        HttpStatus httpStatus,
        String result,
        String message,
        Timestamp timestamp,
        String token) {

}
