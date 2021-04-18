package com.apboutos.moneytrackapi.model;


import org.junit.jupiter.api.Test;


class UserTest {


    @Test
    void getAuthorities() {

        User user = new User("test","test");
        user.setUserRole(User.UserRole.USER);
        System.out.println(user.getAuthorities());

    }
}