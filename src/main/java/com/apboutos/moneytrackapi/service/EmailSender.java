package com.apboutos.moneytrackapi.service;

public interface EmailSender {


    public void send(String to, String token);
}
