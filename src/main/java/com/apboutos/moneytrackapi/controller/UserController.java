package com.apboutos.moneytrackapi.controller;

import com.apboutos.moneytrackapi.controller.Request.UserAuthenticationRequest;
import com.apboutos.moneytrackapi.controller.Request.UserRegistrationRequest;
import com.apboutos.moneytrackapi.controller.Response.UserAuthenticationResponse;
import com.apboutos.moneytrackapi.controller.Response.UserConfirmationResponse;
import com.apboutos.moneytrackapi.controller.Response.UserRegistrationResponse;
import com.apboutos.moneytrackapi.controller.exception.*;
import com.apboutos.moneytrackapi.model.EmailConfirmationToken;
import com.apboutos.moneytrackapi.service.AuthenticationService;
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

    private final AuthenticationService authenticationService;
    private final UserService userService;


    @PostMapping("/register")
    ResponseEntity<UserRegistrationResponse> postUser(@RequestBody UserRegistrationRequest request) throws UsernameTakenException, UserNotSavedException {

        final EmailConfirmationToken token = userService.registerUser(request);
        return ResponseEntity.ok(new UserRegistrationResponse(
                HttpStatus.OK,
                "Succeeded",
                "User created, but not enabled until e-mail confirmation.",
                Timestamp.from(Instant.now()),
                token.getToken()));
    }

    @PatchMapping("/confirm")
    public ResponseEntity<UserConfirmationResponse> confirmUser(@RequestParam String token) throws TokenNotFoundException, TokenExpiredException {

        userService.confirmUser(token, Timestamp.from(Instant.now()));

        return ResponseEntity.ok(new UserConfirmationResponse(
                HttpStatus.OK,
                "Succeeded",
                "Email confirmed. User enabled.",
                Timestamp.from(Instant.now())));
    }

    @PostMapping("/authenticate")
    ResponseEntity<UserAuthenticationResponse> authenticateUser(@RequestBody UserAuthenticationRequest request) {

        final String jwToken = authenticationService.authenticateUser(request);

        return ResponseEntity.accepted().body(new UserAuthenticationResponse(
                HttpStatus.ACCEPTED,
                "Succeeded",
                "User authenticated.",
                Timestamp.from(Instant.now()),
                jwToken));

    }
}
