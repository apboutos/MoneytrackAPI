package com.apboutos.moneytrackapi.model;

import lombok.Data;

import static com.apboutos.moneytrackapi.model.Entry.*;

@Data
public class CategoryDTO {

    private final String name;
    private final Type type;

}
