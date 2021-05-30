package com.apboutos.moneytrackapi.repository;

import com.apboutos.moneytrackapi.model.Category;
import com.apboutos.moneytrackapi.model.CategoryId;
import com.apboutos.moneytrackapi.model.Entry;
import com.apboutos.moneytrackapi.model.User;
import org.junit.jupiter.api.Disabled;
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

        Category expectedCategory = new Category("TestCategory", Entry.Type.Income,testUser);
        categoryRepositoryUnderTest.save(expectedCategory);
        //when
        Optional<Category> actual = categoryRepositoryUnderTest.findCategoryById(expectedCategory.getId());
        Optional<Category> actual2 = categoryRepositoryUnderTest.findCategoryById(new CategoryId("NotExistingCategory", Entry.Type.Income,testUser.getUsername()));

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
        Category category1 = new Category("TestCategory", Entry.Type.Income,testUser);
        Category category2 = new Category("TestCategory", Entry.Type.Income,testUser);
        Category category3 = new Category("TestCategory", Entry.Type.Income,testUser);
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

    @Disabled
    @Test
    void updateCategory(){

        //given
        User testUser = new User("username@test.com","123456789012345678901234567890123456789012345678901234567890");
        userRepository.save(testUser);
        User testUser2 = new User("username2@test.com","123456789012345678901234567890123456789012345678901234567890");
        userRepository.save(testUser2);
        Category category1 = new Category("TestCategory1", Entry.Type.Income,testUser);
        Category category2 = new Category("TestCategory2", Entry.Type.Income,testUser);
        Category category3 = new Category("TestCategory3", Entry.Type.Income,testUser);
        Category category4 = new Category("TestCategory1", Entry.Type.Expense,testUser);
        Category category5 = new Category("TestCategory5", Entry.Type.Expense,testUser);
        Category category6 = new Category("TestCategory6", Entry.Type.Expense,testUser2);

        categoryRepositoryUnderTest.save(category1);
        categoryRepositoryUnderTest.save(category2);
        categoryRepositoryUnderTest.save(category3);

        //when
        categoryRepositoryUnderTest.updateCategory(category4,category1.getId());
        categoryRepositoryUnderTest.updateCategory(category5,category2.getId());
        categoryRepositoryUnderTest.updateCategory(category6,category3.getId());

        //then
        assertThat(categoryRepositoryUnderTest.findCategoryById(category1.getId()).get().getId().getType()).isEqualTo(Entry.Type.Expense);
        assertThat(categoryRepositoryUnderTest.findCategoryById(category2.getId())).isEmpty();
        assertThat(categoryRepositoryUnderTest.findCategoryById(category5.getId())).isNotEmpty();
        assertThat(categoryRepositoryUnderTest.findCategoryById(category6.getId()).get().getUser()).isEqualTo(testUser2);

    }

}