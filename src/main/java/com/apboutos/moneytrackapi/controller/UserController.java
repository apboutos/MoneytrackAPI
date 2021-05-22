package com.apboutos.moneytrackapi.controller;

import com.apboutos.moneytrackapi.controller.Request.UserRegistrationRequest;
import com.apboutos.moneytrackapi.controller.Response.UserConfirmationResponse;
import com.apboutos.moneytrackapi.controller.Response.UserRegistrationResponse;
import com.apboutos.moneytrackapi.controller.exception.*;
import com.apboutos.moneytrackapi.model.EmailConfirmationToken;
import lombok.AllArgsConstructor;
import com.apboutos.moneytrackapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;



@RestController
@RequestMapping(value = "/api/v1/users",produces = "application/json")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    ResponseEntity<UserRegistrationResponse> postUser(@RequestBody UserRegistrationRequest request) throws UsernameTakenException, UserNotSavedException {

        EmailConfirmationToken token = userService.registerUser(request);
        return new ResponseEntity<>(new UserRegistrationResponse(
                HttpStatus.CREATED,
                "Success",
                "User created, but not enabled until e-mail confirmation.",
                Timestamp.from(Instant.now()),
                token.getToken()),HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<UserConfirmationResponse> confirmUser(@RequestParam String token) throws TokenNotFoundException, TokenExpiredException {

        userService.confirmUser(token, Timestamp.from(Instant.now()));

        return new ResponseEntity<>(new UserConfirmationResponse(
                HttpStatus.OK,
                "Success",
                "Email confirmed. User enabled.",
                Timestamp.from(Instant.now())),HttpStatus.OK);
    }
}
