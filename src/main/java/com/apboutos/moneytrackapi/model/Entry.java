package com.apboutos.moneytrackapi.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Entry {

    @Id
    private String id;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(referencedColumnName = "username")
    @NotNull
    private final User username;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Type type;
    @ManyToOne(targetEntity = Category.class)
    @NotNull
    private Category category;
    @Size(max = 45)
    @NotBlank
    private String description;
    @NotNull
    private Double amount;
    @NotNull
    private final Date createdAt;
    private Timestamp lastUpdate;
    @NotNull
    private Boolean isDeleted;

    public enum Type{
        Income,Expense
    }
}
