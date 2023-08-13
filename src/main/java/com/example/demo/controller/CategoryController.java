package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllBooks(Model model) {
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/add")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("category", category);
            return "category/add";
        } else {
            categoryService.saveCategory(category);
            return "redirect:/categories";
        }
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable("id") Long id, Model model){
        Category editCategory = categoryService.getCategoryById(id);
        model.addAttribute("category", editCategory);
        return "category/edit";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("category", category);
            return "category/edit";
        } else {
            categoryService.saveCategory(category);
            return "redirect:/categories";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
