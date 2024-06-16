package com.apboutos.moneytrackapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import static com.apboutos.moneytrackapi.model.Entry.*;

public record CategoryDTO(
        @NotBlank String id,
        @NotBlank String name,
        @NotNull Type type) {

}
