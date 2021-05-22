package com.apboutos.moneytrackapi.repository;

import com.apboutos.moneytrackapi.model.Category;
import com.apboutos.moneytrackapi.model.CategoryId;
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
}