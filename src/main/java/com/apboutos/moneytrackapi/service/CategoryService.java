package com.apboutos.moneytrackapi.service;

import com.apboutos.moneytrackapi.model.Category;
import com.apboutos.moneytrackapi.model.CategoryDTO;
import com.apboutos.moneytrackapi.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;


    public List<CategoryDTO> getAllCategoriesOfUser(String username ){

        List<Category> categories = repository.findCategoriesByUser(username);

        return categories.stream()
                .map((category -> new CategoryDTO(category.getId().getName(),category.getId().getType(), username)))
                .collect(Collectors.toList());
    }
}
