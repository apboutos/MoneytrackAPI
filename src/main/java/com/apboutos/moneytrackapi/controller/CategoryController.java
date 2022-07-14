package com.apboutos.moneytrackapi.controller;

import com.apboutos.moneytrackapi.controller.Response.CategoryCreationResponse;
import com.apboutos.moneytrackapi.controller.Response.CategoryUpdateResponse;
import com.apboutos.moneytrackapi.controller.dto.CategoryDTO;
import com.apboutos.moneytrackapi.controller.exception.CategoryExistsException;
import com.apboutos.moneytrackapi.controller.exception.CategoryNotFoundException;
import com.apboutos.moneytrackapi.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.sql.Timestamp.from;
import static java.time.Instant.now;

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
    public ResponseEntity<CategoryCreationResponse> createCategory(@Valid @RequestBody CategoryDTO categoryDTO, Authentication authentication) throws CategoryExistsException {

        var dto = categoryService.createCategory(categoryDTO, authentication.getName());
        return new ResponseEntity<>(new CategoryCreationResponse(HttpStatus.CREATED,"Success","Category created", from(now()),dto),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CategoryUpdateResponse> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws CategoryNotFoundException {

        categoryService.updateCategory(categoryDTO);
        return new ResponseEntity<>(new CategoryUpdateResponse(HttpStatus.CREATED,"Success","Category updated",from(now()),categoryDTO),HttpStatus.CREATED);
    }
}
