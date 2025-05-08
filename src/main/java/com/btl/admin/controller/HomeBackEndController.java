package com.btl.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.btl.entities.Category;
import com.btl.entities.OrderDetail;
import com.btl.services.CategoryService;
import com.btl.services.OrderService;
import com.btl.services.ProductService;



@Controller
public class HomeBackEndController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @GetMapping(value = { "/backend", "/backend/index", "san-pham", "danh-muc" })
    public String index(Model model) {  
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("products", productService.getAll());
        return "backend/index";
    }

    @GetMapping("/403")
    public String authorize(Model model) {
        return "home/403";
    }
    
    @GetMapping("/forms.html")
    public String form(Model model) {
        List<com.btl.models.OrderDetail> orders = orderService.getAllOrdersWithDetails();
        model.addAttribute("orders", orders);
        return "backend/form";
    }
    @PostMapping("/forms.html/update-status")
    public String updateOrderStatus(@RequestParam("orderId") String orderId,
                                    @RequestParam("status") int status) {
        orderService.updateStatus(orderId, status);
        return "redirect:/forms.html";
    }

}
