package com.apboutos.moneytrackapi.repository;

import com.apboutos.moneytrackapi.model.EmailConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface EmailConfirmationTokenRepository extends JpaRepository<EmailConfirmationToken,Long>, CrudRepository<EmailConfirmationToken,Long> {

    Optional<EmailConfirmationToken> findByToken(String token);

    @Modifying
    @Query("UPDATE EmailConfirmationToken token SET token.confirmedAt = :confirmedAt WHERE token.id = :id")
    void updateTokenConfirmationTimestamp(@Param(value = "id") Long id , @Param(value = "confirmedAt")Timestamp confirmedAt);


}
