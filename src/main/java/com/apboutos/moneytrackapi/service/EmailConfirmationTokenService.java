package com.apboutos.moneytrackapi.service;

import com.apboutos.moneytrackapi.controller.exception.TokenExpiredException;
import com.apboutos.moneytrackapi.controller.exception.TokenNotFoundException;
import com.apboutos.moneytrackapi.model.EmailConfirmationToken;
import com.apboutos.moneytrackapi.repository.EmailConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmailConfirmationTokenService {

    private final EmailConfirmationTokenRepository repository;

    public EmailConfirmationToken saveConfirmationToken(EmailConfirmationToken token){
        return repository.save(token);
    }

    @Transactional
    public EmailConfirmationToken validateEmailConfirmationToken(String token, Timestamp confirmedAt) throws TokenNotFoundException,TokenExpiredException{

        Optional<EmailConfirmationToken> savedToken = repository.findByToken(token);

        if(savedToken.isEmpty()) throw new TokenNotFoundException("Token is invalid.");
        if(savedToken.get().getExpiresAt().before(confirmedAt)) throw new TokenExpiredException("Token has expired.");

        repository.updateTokenConfirmationTimestamp(savedToken.get().getId(),confirmedAt);
        return savedToken.get();
    }

}
