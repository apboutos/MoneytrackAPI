package com.apboutos.moneytrackapi.controller;

import com.apboutos.moneytrackapi.controller.Response.EntriesPutResponse;
import com.apboutos.moneytrackapi.model.Entry;
import com.apboutos.moneytrackapi.service.EntryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/entries", produces = "application/json")
@AllArgsConstructor
public class EntryController {

    private final EntryService entryService;

    @PutMapping
    EntriesPutResponse saveEntries(@RequestBody List<Entry> entries){

        List<List<Entry>> updateResults = entryService.saveEntries(entries);
        return new EntriesPutResponse("201","Success","Entries updated." , updateResults.get(0),updateResults.get(1),updateResults.get(2));
    }
}
