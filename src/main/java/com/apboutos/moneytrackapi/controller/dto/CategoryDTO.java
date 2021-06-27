package com.apboutos.moneytrackapi.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.apboutos.moneytrackapi.model.Entry.*;

@Data
public class CategoryDTO {

    @NotBlank
    private final String id;
    @NotBlank
    private final String name;
    @NotNull
    private final Type type;

}
