package com.apboutos.moneytrackapi.controller;

import com.apboutos.moneytrackapi.controller.Response.UserConfirmationResponse;
import com.apboutos.moneytrackapi.controller.Response.UserRegistrationResponse;
import com.apboutos.moneytrackapi.controller.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameTakenException.class)
    ResponseEntity<Object> handleUserNotSavedException(UsernameTakenException exception, WebRequest webRequest){
        return handleExceptionInternal(exception,
                new UserRegistrationResponse("405","Failure","Username is taken.",Timestamp.from(Instant.now()),null),
                new HttpHeaders(),
                HttpStatus.METHOD_NOT_ALLOWED,
                webRequest
        );
    }

    @ExceptionHandler(EmailTakenException.class)
    ResponseEntity<Object> handleUserNotSavedException(EmailTakenException exception, WebRequest webRequest){
        return handleExceptionInternal(exception,
                new UserRegistrationResponse("405","Failure","e-mail is taken.",Timestamp.from(Instant.now()),null),
                new HttpHeaders(),
                HttpStatus.METHOD_NOT_ALLOWED,
                webRequest
        );
    }

    @ExceptionHandler(UserNotSavedException.class)
    ResponseEntity<Object> handleUserNotSavedException(UserNotSavedException exception, WebRequest webRequest){
        return handleExceptionInternal(exception,
                new UserRegistrationResponse("500","Failure","User not created due to internal error.",Timestamp.from(Instant.now()),null),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                webRequest
        );
    }

    @ExceptionHandler(TokenNotFoundException.class)
    ResponseEntity<Object> handleTokenNotFoundException(TokenNotFoundException exception , WebRequest webRequest){
        return handleExceptionInternal(exception,
                new UserConfirmationResponse("406","Failure","Token is invalid.", Timestamp.from(Instant.now())),
                new HttpHeaders(),
                HttpStatus.NOT_ACCEPTABLE,
                webRequest);
    }

    @ExceptionHandler(TokenExpiredException.class)
    ResponseEntity<Object> handleTokenExpiredException(TokenExpiredException exception, WebRequest webRequest){
        return handleExceptionInternal(exception,
                new UserConfirmationResponse("406","Failure","Token has expired.", Timestamp.from(Instant.now())),
                new HttpHeaders(),
                HttpStatus.NOT_ACCEPTABLE,
                webRequest);
    }

    @ExceptionHandler(CategoryExistsException.class)
    ResponseEntity<Object> handleCategoryExistsException(CategoryExistsException exception, WebRequest webRequest){
        return handleExceptionInternal(exception,
                new UserConfirmationResponse("409","Failure","Category already exists.", Timestamp.from(Instant.now())),
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                webRequest);
    }
}
