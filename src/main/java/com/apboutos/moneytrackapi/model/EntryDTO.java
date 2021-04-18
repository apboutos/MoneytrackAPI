package com.apboutos.moneytrackapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import static com.apboutos.moneytrackapi.model.Entry.*;

@Data
@AllArgsConstructor
public class EntryDTO {

    private String id;
    private String username;
    private Type type;
    private String category;
    private String description;
    private Double amount;
    private Date date;
    private Timestamp lastUpdate;
    private Boolean isDeleted;

    public static EntryDTO createDTOFromEntry(Entry entry){

        return new EntryDTO(entry.getId(),
                entry.getUsername().getUsername(),
                entry.getType(),
                entry.getCategory().getId().getName(),
                entry.getDescription(),
                entry.getAmount(),
                entry.getCreatedAt(),
                entry.getLastUpdate(),
                entry.getIsDeleted());
    }
}

