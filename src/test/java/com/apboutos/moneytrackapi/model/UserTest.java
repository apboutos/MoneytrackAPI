package com.apboutos.moneytrackapi.model;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {


    @Test
    void getAuthorities() {

        User user = new User("test","test","test@gmail.com");
        user.setUserRole(User.UserRole.USER);
        System.out.println(user.getAuthorities());

    }
}