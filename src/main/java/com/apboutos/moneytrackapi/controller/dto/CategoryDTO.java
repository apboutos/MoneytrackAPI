package com.apboutos.moneytrackapi.controller.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
