package com.apboutos.moneytrackapi.controller.Response;

import com.apboutos.moneytrackapi.controller.dto.EntryDTO;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;
import java.util.List;

@Data
@ResponseStatus()
public class UpdateEntriesResponse {

    private final HttpStatus status;
    private final String result;
    private final String message;
    private final Timestamp timestamp;
    private final List<EntryDTO> updatedEntries;
    private final List<EntryDTO> conflictingEntriesOnId;
    private final List<EntryDTO> conflictingEntriesOnCategory;
    private final List<EntryDTO> conflictingEntriesOnLastUpdate;

}
