package com.apboutos.moneytrackapi.model;

import com.apboutos.moneytrackapi.model.Entry.Type;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryId implements Serializable {

    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;

}
