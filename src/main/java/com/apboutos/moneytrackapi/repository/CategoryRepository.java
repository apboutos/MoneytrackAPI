package com.apboutos.moneytrackapi.repository;

import com.apboutos.moneytrackapi.model.Category;
import com.apboutos.moneytrackapi.model.CategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface CategoryRepository extends JpaRepository<Category, CategoryId> {

    Optional<Category> findCategoryById(CategoryId id);

    @Query("select category from Category category join category.users users where users.username =:username")
    List<Category> findCategoriesByUser(String username);

}
