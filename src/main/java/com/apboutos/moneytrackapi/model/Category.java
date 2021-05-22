package com.apboutos.moneytrackapi.model;

import lombok.*;

import javax.persistence.*;
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

    @EmbeddedId
    private CategoryId id;
    private final Timestamp createdAt = Timestamp.from(Instant.now());

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_username" ,referencedColumnName = "username")
    private final User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<Entry> entries;

    public Category(String name, Type type, User user){
        this.id = new CategoryId(name,type,user.getUsername());
        this.user = user;
    }

}
