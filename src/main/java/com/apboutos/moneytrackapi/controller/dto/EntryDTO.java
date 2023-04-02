package com.apboutos.moneytrackapi.controller.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

import static com.apboutos.moneytrackapi.model.Entry.Type;

@SuppressWarnings("unused")
@Data
@RequiredArgsConstructor
public class EntryDTO {

    @NotBlank
    private final String id;
    @NotNull
    private final Type type;
    @NotBlank
    private final String category;
    @NotBlank
    private final String description;
    @NotNull
    private final Double amount;
    @NotNull
    private final Date date;
    @NotNull
    private final Timestamp lastUpdate;
    @NotNull
    private final Boolean isDeleted;

}

