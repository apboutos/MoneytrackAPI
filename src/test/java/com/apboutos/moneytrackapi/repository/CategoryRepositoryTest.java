package com.apboutos.moneytrackapi.repository;

import com.apboutos.moneytrackapi.model.Category;
import com.apboutos.moneytrackapi.model.Entry;
import com.apboutos.moneytrackapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepositoryUnderTest;
    @Autowired
    private UserRepository userRepository;

    @Test
    void findCategoryById() {

        //given
        User testUser = new User("username@test.com","123456789012345678901234567890123456789012345678901234567890");
        userRepository.save(testUser);

        Category expectedCategory = new Category("id1","TestCategory", Entry.Type.Income,testUser);
        categoryRepositoryUnderTest.save(expectedCategory);
        //when
        Optional<Category> actual = categoryRepositoryUnderTest.findCategoryById(expectedCategory.getId());
        Optional<Category> actual2 = categoryRepositoryUnderTest.findCategoryById("NonExistingCategory");

        //then
        assertThat(actual).contains(expectedCategory);
        assertThat(actual2).isEmpty();
    }
    @Test
    void findCategoriesByUser() {

        //given
        User testUser = new User("username@test.com","123456789012345678901234567890123456789012345678901234567890");
        userRepository.save(testUser);

        Set<Category> testCategoriesSet = new HashSet<>();
        Category category1 = new Category("id1","TestCategory", Entry.Type.Income,testUser);
        Category category2 = new Category("id2","TestCategory", Entry.Type.Income,testUser);
        Category category3 = new Category("id3","TestCategory", Entry.Type.Income,testUser);
        testCategoriesSet.add(category1);
        testCategoriesSet.add(category2);
        testCategoriesSet.add(category3);

        categoryRepositoryUnderTest.save(category1);
        categoryRepositoryUnderTest.save(category2);
        categoryRepositoryUnderTest.save(category3);

        //when
        List<Category> actualCategories = categoryRepositoryUnderTest.findCategoriesByUser(testUser);

        //then
        assertThat(new HashSet<>(actualCategories)).isEqualTo(testCategoriesSet);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    void updateCategory(){

        //given
        User testUser = new User("username@test.com","123456789012345678901234567890123456789012345678901234567890");
        userRepository.save(testUser);

        Category category1 = new Category("id1","TestCategory1", Entry.Type.Income,testUser);
        Category category2 = new Category("id2","TestCategory2", Entry.Type.Income,testUser);
        Category category3 = new Category("id3","TestCategory3", Entry.Type.Income,testUser);
        Category updatedCategory1 = new Category("id1","TestCategory1updated", Entry.Type.Expense,testUser);
        Category updatedCategory2 = new Category("id2","TestCategory2updated", Entry.Type.Expense,testUser);
        Category updatedCategory3 = new Category("id6","TestCategory3updated", Entry.Type.Expense,testUser);

        categoryRepositoryUnderTest.save(category1);
        categoryRepositoryUnderTest.save(category2);
        categoryRepositoryUnderTest.save(category3);

        //when
        categoryRepositoryUnderTest.updateCategory(updatedCategory1.getId(),updatedCategory1.getName(),updatedCategory1.getType());
        categoryRepositoryUnderTest.updateCategory(updatedCategory2.getId(),updatedCategory2.getName(),updatedCategory2.getType());
        categoryRepositoryUnderTest.updateCategory(updatedCategory3.getId(),updatedCategory3.getName(),updatedCategory3.getType());

        //then
        assertThat(categoryRepositoryUnderTest.findCategoryById(category1.getId()).get().getType()).isEqualTo(Entry.Type.Expense);
        assertThat(categoryRepositoryUnderTest.findCategoryById(category2.getId()).get().getName()).isEqualTo("TestCategory2updated");
        assertThat(categoryRepositoryUnderTest.findCategoryById(category3.getId()).get().getUser()).isEqualTo(testUser);

    }

}