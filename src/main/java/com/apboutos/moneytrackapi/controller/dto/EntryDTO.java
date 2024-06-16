package com.apboutos.moneytrackapi.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

import static com.apboutos.moneytrackapi.model.Entry.Type;


public record EntryDTO(
        @NotBlank String id,
        @NotNull Type type,
        @NotBlank String category,
        @NotBlank String description,
        @NotNull Double amount,
        @NotNull Date date,
        @NotNull Timestamp lastUpdate,
        @NotNull Boolean isDeleted) {

}

