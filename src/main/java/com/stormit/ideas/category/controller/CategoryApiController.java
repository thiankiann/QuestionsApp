package com.stormit.ideas.category.controller;

import com.stormit.ideas.category.domain.model.Category;
import com.stormit.ideas.category.service.CategoryService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryApiController {
    public CategoryService categoryService;
    public Category category;

    public CategoryApiController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping
    public String indexView(Model model){
        model.addAttribute("categories", categoryService.getCategories());
        return "template";

    }

    @GetMapping("{id}")
    public String singleView(Model model, @PathVariable UUID id){
        model.addAttribute("category", categoryService.getCategory(id));
        return "question/single";
    }

    @GetMapping("add")
    public String addView(Model model){
        model.addAttribute("categoryn", new Category());

        return "question/add";
    }

    @PostMapping
    public String add(Category category){
        categoryService.createCategory(category);

        return "redirect:/categories";
    }
}
