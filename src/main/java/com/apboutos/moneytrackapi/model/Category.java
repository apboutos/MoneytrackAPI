package com.apboutos.moneytrackapi.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;




@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Setter
@Getter
@EqualsAndHashCode(of = {"id"})
public class Category {

    @EmbeddedId
    private CategoryId id;
    private final Timestamp createdAt = Timestamp.from(Instant.now());

    @ManyToMany(mappedBy = "categories")
    private Set<User> users;

    public Category(CategoryId id){
        this.id = id;
    }

}
