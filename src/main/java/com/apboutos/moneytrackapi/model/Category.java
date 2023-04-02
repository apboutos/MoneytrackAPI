package com.apboutos.moneytrackapi.model;

import lombok.*;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static com.apboutos.moneytrackapi.model.Entry.Type;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Setter
@Getter
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
public class Category {

    @Id
    private final String id;
    private String name;
    private Type type;

    private final Timestamp createdAt = Timestamp.from(Instant.now());

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_username" ,referencedColumnName = "username")
    private final User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<Entry> entries;

    public Category(String id, String name, Type type, User user){
        this.id = id;
        this.name = name;
        this.type = type;
        this.user = user;
    }

}
