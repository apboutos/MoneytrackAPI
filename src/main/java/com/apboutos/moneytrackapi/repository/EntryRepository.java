package com.apboutos.moneytrackapi.repository;

import com.apboutos.moneytrackapi.model.Entry;
import com.apboutos.moneytrackapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface EntryRepository extends JpaRepository<Entry, String> {

    Optional<Entry> findEntryById(String id);

    List<Entry> findEntriesByUsernameAndLastUpdateAfter(User user, Timestamp lastPullRequestTimeStamp);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    void deleteEntryById(String id);

}
