package com.apboutos.moneytrackapi.service;

import com.apboutos.moneytrackapi.controller.Request.UserRegistrationRequest;
import com.apboutos.moneytrackapi.controller.exception.*;
import com.apboutos.moneytrackapi.model.EmailConfirmationToken;
import com.apboutos.moneytrackapi.model.User;
import com.apboutos.moneytrackapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final static String USER_NOT_FOUND_MSG = "User with username %s not found";
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailConfirmationTokenService emailConfirmationTokenService;
    private final EmailSender emailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,username)));
    }

    @Transactional
    public EmailConfirmationToken registerUser(UserRegistrationRequest request) throws UserNotSavedException,UsernameTakenException,EmailTakenException {

        User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()), request.getEmail());
        user.setRegistrationDate(Timestamp.from(Instant.now()));
        user.setUserRole(User.UserRole.USER);

        if (repository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameTakenException();
        }

        User databaseSavedUser = repository.save(user);
        if (!user.confirmProperDatabaseSave(databaseSavedUser)) {
            throw new UserNotSavedException("Database failed to save user.");
        }

        EmailConfirmationToken token = new EmailConfirmationToken(
                UUID.randomUUID().toString(),
                databaseSavedUser,
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now().plusSeconds(864000))
        );

        //TODO configure the email server.
        //emailSender.send(request.getEmail(),token.getToken());

        return emailConfirmationTokenService.saveConfirmationToken(token);

    }

    @Transactional
    public void confirmUser(String token, Timestamp confirmedAt) throws TokenExpiredException, TokenNotFoundException {

        EmailConfirmationToken confirmedToken = emailConfirmationTokenService.validateEmailConfirmationToken(token,confirmedAt);

        repository.enableUser(confirmedToken.getUser().getUsername());
    }
}
