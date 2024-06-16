package com.apboutos.moneytrackapi.service;

import com.apboutos.moneytrackapi.controller.exception.CategoryExistsException;
import com.apboutos.moneytrackapi.controller.exception.CategoryNotFoundException;
import com.apboutos.moneytrackapi.model.Category;
import com.apboutos.moneytrackapi.controller.dto.CategoryDTO;
import com.apboutos.moneytrackapi.model.User;
import com.apboutos.moneytrackapi.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    private final UserService userService;

    public List<CategoryDTO> getAllCategoriesOfUser(String username ){

        final List<Category> categories = repository.findCategoriesByUser((User)userService.loadUserByUsername(username));

        return categories.stream()
                .map((category -> new CategoryDTO(category.getId(),category.getName(),category.getType())))
                .collect(Collectors.toList());

    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO, String username) throws CategoryExistsException {

        final User user = (User) userService.loadUserByUsername(username);
        final Category category = new Category(categoryDTO.id(),categoryDTO.name(),categoryDTO.type(),user);

        final Optional<Category> searchResult = repository.findCategoryById(category.getId());
        if (searchResult.isPresent()) {
            throw new CategoryExistsException("This category already exists.");
        }

        final Category result = repository.save(category);
        return new CategoryDTO(result.getId(),result.getName(),result.getType());
    }

    @Transactional
    public void updateCategory(CategoryDTO categoryDTO) throws CategoryNotFoundException {

        final Optional<Category> searchResult = repository.findCategoryById(categoryDTO.id());
        if (searchResult.isEmpty()) {
            throw new CategoryNotFoundException("This category does not exist.");
        }
        repository.updateCategory(categoryDTO.id(),categoryDTO.name(),categoryDTO.type());

    }
}
