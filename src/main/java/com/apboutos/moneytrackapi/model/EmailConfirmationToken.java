package com.apboutos.moneytrackapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
public class EmailConfirmationToken {

    @Id
    @SequenceGenerator(name = "ConfirmationTokenSequence",
            sequenceName = "ConfirmationTokenSequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "ConfirmationTokenSequence")
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private Timestamp createdAt;
    @Column(nullable = false)
    private Timestamp expiresAt;
    private Timestamp confirmedAt;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = false,referencedColumnName = "username")
    private User user;

    public EmailConfirmationToken(String token,User user,Timestamp createdAt, Timestamp expiresAt){
        this.token = token;
        this.user = user;
        this.createdAt = createdAt;
        this.confirmedAt = null;
        this.expiresAt = expiresAt;
    }
}
