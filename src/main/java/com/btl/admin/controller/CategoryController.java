package com.btl.admin.controller;

import com.btl.entities.Category;
import com.btl.services.CategoryService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    // Danh sách + Tìm kiếm
    @GetMapping("/danh-muc/tim-kiem")
    public String search(String searchname, int status, Model model) {
        model.addAttribute("status", status);
        if (searchname == null || searchname.isEmpty()) {
            model.addAttribute("categories", categoryService.getByStatus(status == 1));
        } else {
            model.addAttribute("categories", categoryService.searchByName(searchname));
        }
        return "category/index";
    }

    // Thêm mới - form
    @GetMapping("/danh-muc/them-moi")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("allCategories", categoryService.findAll()); // để chọn parentid
        return "backend/category/create";
    }

    // Thêm mới - submit
    @PostMapping("/danh-muc/ghi")
    public String save(@Valid Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAll());
            return "backend/category/create";
        }
        categoryService.save(category);
        return "redirect:/danh-muc";
    }

    // Xem chi tiết
    @GetMapping("/danh-muc/chi-tiet/{id}")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("category", categoryService.getById(id));
        return "backend/category/detail";
    }

    // Sửa - form
    @GetMapping("/danh-muc/sua/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("category", categoryService.getById(id));
        model.addAttribute("categories", categoryService.getAll());
        return "backend/category/edit";
    }

    // Sửa - submit
    @PostMapping("/danh-muc/cap-nhat")
    public String update(@Valid Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAll());
            return "backend/category/edit";
        }
        categoryService.save(category);
        return "redirect:/danh-muc";
    }

    // Xóa
    @GetMapping("/danh-muc/xoa/{id}")
    public String delete(@PathVariable int id) {
        categoryService.delete(id);
        return "redirect:/danh-muc";
    }
}
