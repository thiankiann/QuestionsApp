package com.stormit.demo.common.controller;

import com.stormit.demo.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

public class IdeasCommonViewController {

    @Autowired
    protected CategoryService categoryService;

    protected void addGlobalAttributes(Model model) {
        model.addAttribute("categories", categoryService.getCategories(
                PageRequest.of(0,10, Sort.by("name").ascending())
        ));
    }
}
