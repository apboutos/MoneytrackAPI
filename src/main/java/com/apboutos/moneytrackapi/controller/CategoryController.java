package com.apboutos.moneytrackapi.controller;

import com.apboutos.moneytrackapi.model.CategoryDTO;
import com.apboutos.moneytrackapi.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO, Authentication authentication){

        var dto = categoryService.createCategory(categoryDTO, authentication.getName());
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
