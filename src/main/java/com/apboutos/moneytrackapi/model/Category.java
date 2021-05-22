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
@EqualsAndHashCode(of = {"id","createdAt"})
public class Category {

    @EmbeddedId
    private CategoryId id;
    private final Timestamp createdAt = Timestamp.from(Instant.now());

    @ManyToMany(mappedBy = "categories")
    private Set<User> users;

    public Category(CategoryId id){
        this.id = id;
    }

     /*
        We cannot use lombok default implementation of equals and hashcode because lombok uses the list of users
        inside the hashcode implementation, and this creates a stackoverflow error due to recursive call of hashcode
        between User and Category.
     */
    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id.equals(category.id) && createdAt.equals(category.createdAt);
    }

    @Override
    public int hashCode() {
        int hash = 7;

        hash = 31 * hash + (id == null ? 0 : id.hashCode());
        hash = 31 * hash + (createdAt == null ? 0 : createdAt.hashCode());

        return hash;
    }*/
}
