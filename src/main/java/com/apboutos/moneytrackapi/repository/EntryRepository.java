package com.apboutos.moneytrackapi.repository;

import com.apboutos.moneytrackapi.model.Category;
import com.apboutos.moneytrackapi.model.Entry;
import com.apboutos.moneytrackapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface EntryRepository extends JpaRepository<Entry, String> {

    Optional<Entry> findEntryById(String id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE Entry e SET e.username = :username, e.type = :type, e.category = :category, e.description = :description, e.amount = :amount, " +
            "e.createdAt = :date, e.lastUpdate = :lastUpdate, e.isDeleted =:isDeleted WHERE e.id =:id")
    void update(@Param("id") String id,
                @Param("username") User username,
                @Param("type") Entry.Type type,
                @Param("category") Category category,
                @Param("description") String description,
                @Param("amount") Double amount,
                @Param("date") Date date,
                @Param("lastUpdate") Timestamp lastUpdate,
                @Param("isDeleted") Boolean isDeleted);
}
