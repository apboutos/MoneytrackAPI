package com.apboutos.moneytrackapi.service;

import com.apboutos.moneytrackapi.controller.Response.CreateEntriesResponse;
import com.apboutos.moneytrackapi.controller.Response.DeleteEntriesResponse;
import com.apboutos.moneytrackapi.controller.Response.GetEntriesResponse;
import com.apboutos.moneytrackapi.controller.Response.UpdateEntriesResponse;
import com.apboutos.moneytrackapi.model.Category;
import com.apboutos.moneytrackapi.model.Entry;
import com.apboutos.moneytrackapi.controller.dto.EntryDTO;
import com.apboutos.moneytrackapi.model.User;
import com.apboutos.moneytrackapi.repository.CategoryRepository;
import com.apboutos.moneytrackapi.repository.EntryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.sql.Timestamp.from;
import static java.time.Instant.now;

@SuppressWarnings("DuplicatedCode")
@Service
@AllArgsConstructor
public class EntryService {

    private final EntryRepository entryRepository;
    private final UserService userService;
    private final CategoryRepository categoryRepository;


    public CreateEntriesResponse saveEntries(List<EntryDTO> entries, String username) throws IllegalArgumentException {

        User user = (User) userService.loadUserByUsername(username);
        List<EntryDTO> savedEntries = new ArrayList<>();
        List<EntryDTO> conflictingEntriesOnId = new ArrayList<>();
        List<EntryDTO> conflictingEntriesOnCategory = new ArrayList<>();

        for (EntryDTO entry : entries) {

            Optional<Entry> entrySearchResult = entryRepository.findEntryById(entry.getId());
            if (entrySearchResult.isPresent())
                conflictingEntriesOnId.add(entry);
            else {
                Optional<Category> categorySearchResult = categoryRepository.findCategoryById(entry.getCategory());
                if (categorySearchResult.isEmpty())
                    conflictingEntriesOnCategory.add(entry);
                else {
                    Entry newEntry = entryRepository.save(createEntryFromDTO(entry, user, categorySearchResult.get()));
                    savedEntries.add(createDTOFromEntry(newEntry));
                }
            }
        }
        if (conflictingEntriesOnId.isEmpty() && conflictingEntriesOnCategory.isEmpty())
            return new CreateEntriesResponse(
                    HttpStatus.OK,
                    "Success",
                    "All entries have been created.",
                    from(now()),
                    savedEntries,
                    conflictingEntriesOnId,
                    conflictingEntriesOnCategory);
        else
            return new CreateEntriesResponse(
                    HttpStatus.CONFLICT,
                    "Failure",
                    "Attempt to create conflicting entries.",
                    from(now()),
                    savedEntries,
                    conflictingEntriesOnId,
                    conflictingEntriesOnCategory);
    }

    public GetEntriesResponse getEntries(Timestamp lastPullRequestTimestamp, String username) {

        User user = (User) userService.loadUserByUsername(username);

        List<Entry> entriesReturnedBySearch = entryRepository.findEntriesByUsernameAndLastUpdateAfter(user, lastPullRequestTimestamp);
        List<EntryDTO> entries = entriesReturnedBySearch.stream().map(this::createDTOFromEntry).collect(Collectors.toList());

        return new GetEntriesResponse(HttpStatus.OK, "Success", "Returning entries updated after " + lastPullRequestTimestamp, from(now()), entries);
    }

    @Transactional
    public DeleteEntriesResponse deleteEntries(List<EntryDTO> entries) {

        List<EntryDTO> conflictingEntries = new ArrayList<>();
        entries.forEach(e -> {
            entryRepository.deleteEntryById(e.getId());
            if (entryRepository.findEntryById(e.getId()).isPresent())
                conflictingEntries.add(e);
        });

        if (conflictingEntries.isEmpty())
            return new DeleteEntriesResponse(HttpStatus.OK, "Success", "All entries have been deleted", from(now()), new ArrayList<>());
        else
            return new DeleteEntriesResponse(HttpStatus.CONFLICT, "Failure", "Some entries could not be deleted", from(now()), conflictingEntries);
    }

    @Transactional
    public UpdateEntriesResponse updateEntries(List<EntryDTO> entries, String username) {

        User user = (User) userService.loadUserByUsername(username);
        List<EntryDTO> updatedEntries = new ArrayList<>();
        List<EntryDTO> conflictingEntriesOnId = new ArrayList<>();
        List<EntryDTO> conflictingEntriesOnCategory = new ArrayList<>();
        List<EntryDTO> conflictingEntriesOnLastUpdate = new ArrayList<>();

        for (EntryDTO entry : entries) {

            Optional<Entry> result = entryRepository.findEntryById(entry.getId());
            Optional<Category> categorySearchResult = categoryRepository.findCategoryById(entry.getCategory());

            if (result.isEmpty())
                conflictingEntriesOnId.add(entry);
            else if (categorySearchResult.isEmpty())
                conflictingEntriesOnCategory.add(entry);
            else if (result.get().getLastUpdate().after(entry.getLastUpdate()))
                conflictingEntriesOnLastUpdate.add(entry);
            else {
                entryRepository.deleteEntryById(entry.getId());
                Entry savedEntry = entryRepository.save(createEntryFromDTO(entry, user, new Category(entry.getCategory(),entry.getCategory(), entry.getType(), user)));
                updatedEntries.add(createDTOFromEntry(savedEntry));
            }
        }

        if (conflictingEntriesOnId.isEmpty() && conflictingEntriesOnCategory.isEmpty() && conflictingEntriesOnLastUpdate.isEmpty())
            return new UpdateEntriesResponse(
                    HttpStatus.CREATED,
                    "Success",
                    "All entries have been updated.",
                    from(now()),
                    updatedEntries,
                    conflictingEntriesOnId,
                    conflictingEntriesOnCategory,
                    conflictingEntriesOnLastUpdate);
        else
            return new UpdateEntriesResponse(
                    HttpStatus.CONFLICT,
                    "Failure",
                    "Attempt to update conflicting entries.",
                    from(now()),
                    updatedEntries,
                    conflictingEntriesOnId,
                    conflictingEntriesOnCategory,
                    conflictingEntriesOnLastUpdate);
    }

    private Entry createEntryFromDTO(EntryDTO entryDTO, User user, Category category) {
        return new Entry(
                entryDTO.getId(),
                user, entryDTO.getType(),
                category,
                entryDTO.getDescription(),
                entryDTO.getAmount(),
                entryDTO.getDate(),
                entryDTO.getLastUpdate(),
                entryDTO.getIsDeleted());
    }

    private EntryDTO createDTOFromEntry(Entry entry) {
        return new EntryDTO(
                entry.getId(),
                entry.getType(),
                entry.getCategory().getName(),
                entry.getDescription(),
                entry.getAmount(),
                entry.getCreatedAt(),
                entry.getLastUpdate(),
                entry.getIsDeleted());

    }


}

