package com.apboutos.moneytrackapi.controller;

import com.apboutos.moneytrackapi.controller.dto.CategoryDTO;
import com.apboutos.moneytrackapi.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryServiceMock;

    @Mock
    private Authentication authentication;
    @Test
    public void getAllCategories(){

        Mockito.when(authentication.getName()).thenReturn("TestUser");
        System.out.println(categoryController);
        Mockito.when(categoryServiceMock.getAllCategoriesOfUser("TestUser")).thenReturn(new ArrayList<>());

        List<CategoryDTO> result = categoryController.getAllCategories(authentication);

        Mockito.verify(categoryServiceMock, Mockito.only()).getAllCategoriesOfUser("TestUser");

        assertThat(result).isEmpty();
    }

}