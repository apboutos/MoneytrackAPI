package com.apboutos.moneytrackapi.controller.Response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;

@Data
@RequiredArgsConstructor
@ResponseStatus(code = HttpStatus.OK , reason = "User registered.")
public class UserRegistrationResponse {

    private final String status;
    private final String result;
    private final String message;
    private final Timestamp timestamp;
    private final String token;
}
