package com.apboutos.moneytrackapi.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

import static com.apboutos.moneytrackapi.model.Entry.*;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Category {
    @Id
    @Size(max = 45)
    @NotBlank
    private String name;
    private final Timestamp createdAt = Timestamp.from(Instant.now());

    @Enumerated(EnumType.STRING)
    @NotNull
    private Type type;

    @ManyToMany(mappedBy = "categories")
    private Set<User> users;

    public Category(String name, Type type){
        this.name = name;
        this.type = type;
    }
}
