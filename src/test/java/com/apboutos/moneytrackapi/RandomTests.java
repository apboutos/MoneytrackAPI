package com.apboutos.moneytrackapi;

import com.apboutos.moneytrackapi.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

@RunWith(JUnit4.class)
public class RandomTests {


    @Test
    public void testTimestamp(){

        User user = new User();
        System.out.println(user.getId());
        System.out.println(Timestamp.from(Instant.now()));
    }


}
