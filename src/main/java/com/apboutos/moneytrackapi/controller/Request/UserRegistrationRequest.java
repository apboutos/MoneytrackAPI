package com.apboutos.moneytrackapi.controller.Request;

import lombok.Data;

public record UserRegistrationRequest(
        String username,
        String password
) { }
