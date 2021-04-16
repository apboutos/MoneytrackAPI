package com.apboutos.moneytrackapi.service;

import com.apboutos.moneytrackapi.model.Category;
import com.apboutos.moneytrackapi.model.Entry;
import com.apboutos.moneytrackapi.model.EntryDTO;
import com.apboutos.moneytrackapi.model.User;
import com.apboutos.moneytrackapi.repository.CategoryRepository;
import com.apboutos.moneytrackapi.repository.EntryRepository;
import com.apboutos.moneytrackapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EntryService {

    private final EntryRepository repository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    public void saveEntry(EntryDTO entryDTO) throws IllegalArgumentException{

        Optional<Entry> savedEntry = repository.findEntryById(entryDTO.getId());
        if(savedEntry.isEmpty())
            repository.save(createEntryFromDTO(entryDTO));
        else if(savedEntry.get().getLastUpdate().before(entryDTO.getLastUpdate())) {
            Entry entry = createEntryFromDTO(entryDTO);
            repository.update(entry.getId(),entry.getUsername(),entry.getType(),entry.getCategory(),entry.getDescription(), entry.getAmount(),entry.getCreatedAt(),entry.getLastUpdate(),entry.getIsDeleted());
        }
    }

    private Entry createEntryFromDTO(EntryDTO entryDTO) throws IllegalArgumentException {

        User user = userRepository.findByUsername(entryDTO.getUsername()).orElseThrow(IllegalArgumentException::new);
        Category category = categoryRepository.findById(entryDTO.getCategory()).orElseThrow(IllegalArgumentException::new);
        return new Entry(entryDTO.getId(), user, entryDTO.getType(),category,entryDTO.getDescription(),
                entryDTO.getAmount(),entryDTO.getDate(),entryDTO.getLastUpdate(),entryDTO.getIsDeleted());

    }

}
