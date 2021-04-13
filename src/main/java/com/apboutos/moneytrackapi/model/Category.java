package com.apboutos.moneytrackapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
public class Category {
    @Id
    @Size(max = 45)
    @NotBlank
    private String name;
    private final Timestamp createdAt = Timestamp.from(Instant.now());

    public Category(String name){
        this.name = name;
    }
}
