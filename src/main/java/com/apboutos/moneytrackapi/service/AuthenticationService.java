package com.apboutos.moneytrackapi.service;

import com.apboutos.moneytrackapi.controller.Request.UserAuthenticationRequest;
import com.apboutos.moneytrackapi.model.User;
import com.apboutos.moneytrackapi.notification.MessageSender;
import com.apboutos.moneytrackapi.repository.UserRepository;
import com.apboutos.moneytrackapi.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final JWTService jwtService;
    private final MessageSender messageSender;

    public String authenticateUser(UserAuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password())
        );

        final User user = repository.findByUsername((request.username()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        messageSender.sendMessage("User" + user.getUsername() + " logged in");
        return jwtService.generateToken(user);

    }

}
