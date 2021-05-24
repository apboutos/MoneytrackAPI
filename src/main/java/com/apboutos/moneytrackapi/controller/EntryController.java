package com.apboutos.moneytrackapi.controller;

import com.apboutos.moneytrackapi.controller.Response.CreateEntriesResponse;
import com.apboutos.moneytrackapi.controller.Response.DeleteEntriesResponse;
import com.apboutos.moneytrackapi.controller.Response.GetEntriesResponse;
import com.apboutos.moneytrackapi.controller.dto.EntryDTO;
import com.apboutos.moneytrackapi.service.EntryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/entries", produces = "application/json")
@AllArgsConstructor
public class EntryController {

    private final EntryService entryService;

    @PostMapping
    ResponseEntity<CreateEntriesResponse> createEntries(@Valid @RequestBody List<EntryDTO> entries, Authentication authentication) {

        CreateEntriesResponse response = entryService.saveEntries(entries,authentication.getName());
        return new ResponseEntity<>(response,response.getStatus());
    }

    @GetMapping
    ResponseEntity<GetEntriesResponse> getEntries(@RequestParam("lastPullRequestTimestamp") @NotBlank String lastPullRequestTimestamp, Authentication authentication){

        LocalDateTime localDateTime = LocalDateTime.parse(lastPullRequestTimestamp);
        GetEntriesResponse response = entryService.getEntries(Timestamp.valueOf(localDateTime),authentication.getName());

        return new ResponseEntity<>(response,response.getStatus());
    }

    @DeleteMapping
    ResponseEntity<DeleteEntriesResponse> deleteEntries(List<EntryDTO> entries, Authentication authentication){

        DeleteEntriesResponse response = entryService.deleteEntries(entries, authentication.getName());

        return new ResponseEntity<>(response,response.getStatus());
    }

}
