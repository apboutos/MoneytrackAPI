package com.apboutos.moneytrackapi.service;

import org.springframework.scheduling.annotation.Async;

public interface EmailSender {

    @Async
    void send(String to, String token);
}
