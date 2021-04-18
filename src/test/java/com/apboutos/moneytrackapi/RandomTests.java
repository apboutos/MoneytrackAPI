package com.apboutos.moneytrackapi;

import com.apboutos.moneytrackapi.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

@RunWith(JUnit4.class)
public class RandomTests {


    @Test
    public void testTimestamp(){

        User user = new User();
        System.out.println(user.getUsername());
        System.out.println(Timestamp.from(Instant.now()));
    }

    @Test
    public void BCryptTest(){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("ma582468"));
    }


}
