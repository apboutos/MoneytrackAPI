package com.apboutos.moneytrackapi.repository;

import com.apboutos.moneytrackapi.model.Category;
import com.apboutos.moneytrackapi.model.CategoryId;
import com.apboutos.moneytrackapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface CategoryRepository extends JpaRepository<Category, CategoryId> {

    Optional<Category> findCategoryById(CategoryId id);

    List<Category> findCategoriesByUser(User user);

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("UPDATE Category c SET c = :category WHERE c.id = :id")
    void updateCategory(@Param(value = "category") Category category, @Param(value = "id") CategoryId id);

}
