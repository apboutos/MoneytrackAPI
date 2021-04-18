package com.apboutos.moneytrackapi.controller;

import com.apboutos.moneytrackapi.model.CategoryDTO;
import com.apboutos.moneytrackapi.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/categories",produces = "application/json")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getAllCategories(Authentication authentication){

        return categoryService.getAllCategoriesOfUser(authentication.getName());
    }
}
