package com.apboutos.moneytrackapi.controller.Response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
public class UserRegistrationResponse {

    private final HttpStatus status;
    private final String result;
    private final String message;
    private final Timestamp timestamp;
    private final String token;
}
