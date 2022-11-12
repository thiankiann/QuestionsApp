package com.stormit.demo.category.service;

import com.stormit.demo.category.model.Category;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    public List<Category> getCategories() {
        return Arrays.asList( new Category("Category 1" ), new Category("Category 2"));
    }

    public Category getCategory(UUID id) {
        return new Category("Category " +id);
    }

    public Category createCategory(Category Category) {

        Category.setId(UUID.randomUUID());
        return Category;
    }

    public void deleteCategory(UUID id) {
    }

    public Category updateCategory(UUID id, Category Category) {
        return Category;
    }
}
