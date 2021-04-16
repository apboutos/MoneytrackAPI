package com.apboutos.moneytrackapi.service;

import com.apboutos.moneytrackapi.model.Entry;
import com.apboutos.moneytrackapi.repository.EntryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EntryService {

    private final EntryRepository repository;

    public List<List<Entry>> saveEntries(List<Entry> entries){

        List<Entry> savedEntries = new ArrayList<>();
        List<Entry> unsavedDueToOlderVersionEntries = new ArrayList<>();
        List<Entry> unsavedDueToErrorEntries = new ArrayList<>();

        for(Entry entry : entries){

            Optional<Entry> entryInDatabase = repository.findEntryById(entry.getId());

            if(entryInDatabase.isEmpty()) {
                if(entry.equals(repository.save(entry)))
                    savedEntries.add(entry);
                else
                    unsavedDueToErrorEntries.add(entry);
            }
            else{
                if(entry.getLastUpdate().after(entryInDatabase.get().getLastUpdate())){
                    repository.update(entry.getId(),entry.getUsername(),entry.getType(),entry.getCategory(),entry.getDescription(), entry.getAmount(),entry.getCreatedAt(),entry.getLastUpdate(),entry.getIsDeleted());
                    if(entry.equals(repository.getOne(entry.getId())))
                        savedEntries.add(entry);
                    else
                        unsavedDueToErrorEntries.add(entry);
                }
                else
                    unsavedDueToOlderVersionEntries.add(entry);
            }
        }

        return Arrays.asList(savedEntries,unsavedDueToOlderVersionEntries,unsavedDueToErrorEntries);
    }
}
