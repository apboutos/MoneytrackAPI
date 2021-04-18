package com.apboutos.moneytrackapi.model;

import com.apboutos.moneytrackapi.model.Entry.Type;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryId implements Serializable {

    private String name;
    private Type type;

}
