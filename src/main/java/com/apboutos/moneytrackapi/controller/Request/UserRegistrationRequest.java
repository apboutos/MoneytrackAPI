package com.apboutos.moneytrackapi.controller.Request;

import lombok.Data;

@Data
public class UserRegistrationRequest {

    private String username;
    private String password;
    private String email;

}
