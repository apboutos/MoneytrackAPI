package com.apboutos.moneytrackapi.controller;

import com.apboutos.moneytrackapi.controller.Request.UserRegistrationRequest;
import com.apboutos.moneytrackapi.controller.Response.UserConfirmationResponse;
import com.apboutos.moneytrackapi.controller.Response.UserRegistrationResponse;
import com.apboutos.moneytrackapi.controller.exception.*;
import com.apboutos.moneytrackapi.model.EmailConfirmationToken;
import lombok.AllArgsConstructor;
import com.apboutos.moneytrackapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;



@RestController
@RequestMapping(value = "/API/User",produces = "application/json")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping(value = "/")
    UserRegistrationResponse postUser(@RequestBody UserRegistrationRequest request) throws UsernameTakenException, EmailTakenException, UserNotSavedException {

        EmailConfirmationToken token = userService.registerUser(request);

        return new UserRegistrationResponse(
                "201",
                "Success",
                "User created, but not enabled until e-mail confirmation.",
                Timestamp.from(Instant.now()),
                token.getToken());
    }

    @PatchMapping(value = "/confirm")
    public UserConfirmationResponse confirmUser(@RequestParam String token) throws TokenNotFoundException, TokenExpiredException {

        userService.confirmUser(token, Timestamp.from(Instant.now()));

        return new UserConfirmationResponse(
                "200",
                "Success",
                "Email confirmed. User enabled.",
                Timestamp.from(Instant.now()));
    }
}
