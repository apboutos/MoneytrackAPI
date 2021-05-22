package com.apboutos.moneytrackapi.model;

import com.apboutos.moneytrackapi.model.Entry.Type;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"name","type","username"})
public class CategoryId implements Serializable {

    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String username;

}
