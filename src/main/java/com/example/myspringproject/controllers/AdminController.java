package com.example.myspringproject.controllers;

import com.example.myspringproject.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

//    @GetMapping("admin/product/add")
//    public String addProduct(Model model){
//        model.addAttribute("product", new Product());
//        model.addAttribute("category");
//    }
}
