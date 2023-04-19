package com.stormit.demo.category.controller;

import com.stormit.demo.category.domain.model.Category;
import com.stormit.demo.category.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryApiController {

    private final CategoryService categoryService;

    public CategoryApiController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    Page<Category> getCategories(Pageable pageable) {
        return categoryService.getCategories(pageable);
    }

    @GetMapping("{id}")
    Category getCategory(@PathVariable UUID id) {
        return categoryService.getCategory(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Category updateCategory(@PathVariable UUID id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
    }
}
