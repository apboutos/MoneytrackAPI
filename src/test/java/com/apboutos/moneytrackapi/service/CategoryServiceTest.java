package com.apboutos.moneytrackapi.service;

import com.apboutos.moneytrackapi.controller.dto.CategoryDTO;
import com.apboutos.moneytrackapi.model.Category;
import com.apboutos.moneytrackapi.model.Entry;
import com.apboutos.moneytrackapi.model.User;
import com.apboutos.moneytrackapi.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService classUnderTest;

    @Mock
    private CategoryRepository categoryRepositoryMock;

    @Mock
    private UserService userServiceMock;

    @Mock
    private User mockUser;

    @Test
    void getAllCategoriesOfUser() {

        Mockito.when(userServiceMock.loadUserByUsername(any(String.class))).thenReturn(mockUser);
        List<Category> mockList = new ArrayList<>();
        mockList.add(new Category("testId","testName", Entry.Type.Income, mockUser));
        Mockito.when(categoryRepositoryMock.findCategoriesByUser(any(User.class))).thenReturn(mockList);

        List<CategoryDTO> actualResult = classUnderTest.getAllCategoriesOfUser("TestUser");

        assertThat(actualResult.size()).isEqualTo(1);
        assertThat(actualResult.get(0).getId()).isEqualTo("testId");
        assertThat(actualResult.get(0).getName()).isEqualTo("testName");
        assertThat(actualResult.get(0).getType()).isEqualTo(Entry.Type.Income);
    }

    @Test
    void createCategory() {
    }

    @Test
    void updateCategory() {
    }
}