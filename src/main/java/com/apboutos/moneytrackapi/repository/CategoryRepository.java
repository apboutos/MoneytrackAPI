package com.apboutos.moneytrackapi.repository;

import com.apboutos.moneytrackapi.model.Category;
import com.apboutos.moneytrackapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.apboutos.moneytrackapi.model.Entry.Type;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findCategoryById(String id);

    List<Category> findCategoriesByUser(User user);

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("UPDATE Category c SET c.name = :name,c.type = :type WHERE c.id = :id")
    void updateCategory(@Param(value = "id") String id, @Param(value = "name") String name, @Param(value = "type") Type type);

}
