package com.apboutos.moneytrackapi.repository;

import com.apboutos.moneytrackapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepositoryUnderTest;

    @Test
    void findByUsername() {
        //given
        User user = new User("testUser@test.com","123456789012345678901234567890123456789012345678901234567890");
        userRepositoryUnderTest.save(user);

        //when
        Optional<User> actual1 = userRepositoryUnderTest.findByUsername("testUser@test.com");
        Optional<User> actual2 = userRepositoryUnderTest.findByUsername("");

        //then
        assertThat(actual1).contains(user);
        assertThat(actual2).isEmpty();
    }

    @Test
    void enableUser() {
        //given
        User user = new User("testUser@test.com","123456789012345678901234567890123456789012345678901234567890");
        userRepositoryUnderTest.save(user);

        //when
        userRepositoryUnderTest.enableUser("testUser@test.com");
        //userRepositoryUnderTest.updatePassword("000000000000000000000000000000000000000000000000000000000000","testUser@test.com");
        Optional<User> actual = userRepositoryUnderTest.findByUsername("testUser@test.com");


        //then
        assertThat(actual.get().getEnabled()).isTrue();
        System.out.println(actual.get().getPassword());
    }

    @Test
    void updatePassword(){

        //given
        User user = new User("testUser@test.com","123456789012345678901234567890123456789012345678901234567890");
        userRepositoryUnderTest.save(user);
        String testInputPassword = "000000000000000000000000000000000000000000000000000000000000";

        //when
        userRepositoryUnderTest.updatePassword( testInputPassword,"testUser@test.com");
        Optional<User> actual = userRepositoryUnderTest.findByUsername("testUser@test.com");


        //then
        assertThat(actual.get().getPassword()).isEqualTo(testInputPassword);
    }
}