package com.apboutos.moneytrackapi.model;

import com.apboutos.moneytrackapi.model.Entry.Type;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"name","type"})
public class CategoryId implements Serializable {

    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;

}
