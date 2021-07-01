package com.apboutos.moneytrackapi.repository;

import com.apboutos.moneytrackapi.model.Category;
import com.apboutos.moneytrackapi.model.Entry;
import com.apboutos.moneytrackapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("UPDATE Entry e SET e.description = :description, e.type = :type, e.amount = :amount, e.category = :category," +
            "e.lastUpdate = :lastUpdate, e.isDeleted = :isDeleted WHERE e.id = :id")
    void updateEntry(@Param(value = "id") String id,
                     @Param(value = "type") Entry.Type type,
                     @Param(value = "description") String description,
                     @Param(value = "amount") Double amount,
                     @Param(value = "category") Category category,
                     @Param(value = "lastUpdate") Timestamp lastUpdate,
                     @Param(value = "isDeleted") Boolean isDeleted);

}
