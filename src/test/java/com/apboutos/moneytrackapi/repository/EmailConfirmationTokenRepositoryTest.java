package com.apboutos.moneytrackapi.repository;

import com.apboutos.moneytrackapi.model.EmailConfirmationToken;
import com.apboutos.moneytrackapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.Optional;

import static java.sql.Timestamp.from;
import static java.time.Instant.now;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@DataJpaTest
class EmailConfirmationTokenRepositoryTest {

    @Autowired
    private EmailConfirmationTokenRepository emailConfirmationTokenRepositoryUnderTest;
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByToken() {

        //given
        User testUser = userRepository.save(new User("testUser@test.com","000000000000000000000000000000000000000000000000000000000000"));
        EmailConfirmationToken expected = emailConfirmationTokenRepositoryUnderTest.save(new EmailConfirmationToken("testToken",testUser, from(now()), from(now())));

        //when
        Optional<EmailConfirmationToken> actual = emailConfirmationTokenRepositoryUnderTest.findByToken("testToken");

        //then
        assertThat(actual).contains(expected);
        assertThat(actual.get().getConfirmedAt()).isNull();
    }

    @Test
    void updateTokenConfirmationTimestamp() {

        //given
        User testUser = userRepository.save(new User("testUser@test.com","000000000000000000000000000000000000000000000000000000000000"));
        EmailConfirmationToken expected = emailConfirmationTokenRepositoryUnderTest.save(new EmailConfirmationToken("testToken",testUser, from(now()), from(now())));
        Timestamp confirmationDate = from(now());

        //when
        emailConfirmationTokenRepositoryUnderTest.updateTokenConfirmationTimestamp(expected.getId(),confirmationDate);
        Optional<EmailConfirmationToken> actual = emailConfirmationTokenRepositoryUnderTest.findByToken("testToken");

        //then
        assertThat(actual.get().getConfirmedAt().getTime()).isEqualTo(confirmationDate.getTime());

    }
}