package com.apboutos.moneytrackapi.service;

import com.apboutos.moneytrackapi.controller.exception.CategoryExistsException;
import com.apboutos.moneytrackapi.controller.exception.CategoryNotFoundException;
import com.apboutos.moneytrackapi.model.Category;
import com.apboutos.moneytrackapi.controller.dto.CategoryDTO;
import com.apboutos.moneytrackapi.model.User;
import com.apboutos.moneytrackapi.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    private final UserService userService;

    public List<CategoryDTO> getAllCategoriesOfUser(String username ){

        List<Category> categories = repository.findCategoriesByUser((User)userService.loadUserByUsername(username));

        return categories.stream()
                .map((category -> new CategoryDTO(category.getId().getName(),category.getId().getType())))
                .collect(Collectors.toList());

    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO, String username) throws CategoryExistsException {

        User user = (User)userService.loadUserByUsername(username);
        Category category = new Category(categoryDTO.getName(),categoryDTO.getType(),user);

        Optional<Category> searchResult = repository.findCategoryById(category.getId());
        if(searchResult.isPresent()) throw new CategoryExistsException("This category already exists.");

        Category result = repository.save(category);
        return new CategoryDTO(result.getId().getName(),result.getId().getType());
    }


    public CategoryDTO updateCategory(CategoryDTO categoryDTO, String username) throws CategoryNotFoundException {

        User user = (User)userService.loadUserByUsername(username);
        Category category = new Category(categoryDTO.getName(),categoryDTO.getType(),user);

        Optional<Category> searchResult = repository.findCategoryById(category.getId());
        if(searchResult.isEmpty()) throw new CategoryNotFoundException("This category already exists.");

        repository.updateCategory(category,category.getId());

        return categoryDTO;
    }

}
