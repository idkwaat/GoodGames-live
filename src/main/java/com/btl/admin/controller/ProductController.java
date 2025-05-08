package com.btl.admin.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.btl.entities.Product;
import com.btl.services.CategoryService;
import com.btl.services.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;

    @Autowired
    CategoryService categoryService;
	@GetMapping("/san-pham/tim-kiem")
	public String search(String searchname, int active, Model model) {
		model.addAttribute("active",active);
		if(searchname.isEmpty())
		{
			model.addAttribute("products",productService.getByActive(active==1?true:false));
			return "index";
		}else
		{
			model.addAttribute("products",productService.searchName(searchname));
			return "index";
		}
	}
	@GetMapping("/san-pham/them-moi")
	public String create(Model model) {
	    model.addAttribute("product", new Product());
	    model.addAttribute("categories", categoryService.findAll());
	    return "backend/product/create";
	}

	
	@PostMapping("/san-pham/ghi")
	public String create(@Valid Product product,
	                     BindingResult result,
	                     @RequestParam("imageFile") MultipartFile imageFile,
	                     Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("product", product);
	        return "backend/product/create";
	    }

	    try {
	        // Lưu file ảnh nếu có
	        if (!imageFile.isEmpty()) {
	            String uploadDir = "uploads";
	            Path uploadPath = Paths.get(uploadDir);
	            if (!Files.exists(uploadPath)) {
	                Files.createDirectories(uploadPath);
	            }

	            String fileName = imageFile.getOriginalFilename();
	            Path filePath = uploadPath.resolve(fileName);
	            imageFile.transferTo(filePath.toFile());

	            // Lưu tên ảnh vào thuộc tính pictures
	            product.setPictures(fileName);
	        }

	        productService.save(product);
	    } catch (IOException e) {
	        e.printStackTrace();
	        model.addAttribute("uploadError", "Không thể lưu ảnh.");
	        return "backend/product/create";
	    }

	    return "redirect:/san-pham";
	}


	
	@GetMapping("/san-pham/xoa/{id}")
	public String delete(@PathVariable int id) {
		productService.delete(id);
		return "redirect:/san-pham";
	}

	@GetMapping("/san-pham/chi-tiet/{id}")
	public String detail(@PathVariable int id,Model model) {
		model.addAttribute("product",productService.getById(id));
		return "backend/product/detail";
	}
	
	@GetMapping("/san-pham/sua/{id}")
	public String edit(@PathVariable int id, Model model) {
		model.addAttribute("product",productService.getById(id));
		return "backend/product/edit";
	}
	
	@PostMapping("/san-pham/cap-nhat")
	public String edit(@Valid Product product, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("product",product);
			return "backend/product/edit";
		}
		productService.save(product);
		return "redirect:/san-pham";
	}
}