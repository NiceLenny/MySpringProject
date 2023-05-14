package com.example.myspringproject.controllers;

import com.example.myspringproject.models.*;
import com.example.myspringproject.repositories.CategoryRepository;
import com.example.myspringproject.services.OrderService;
import com.example.myspringproject.services.PersonService;
import com.example.myspringproject.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AdminController {

    private final ProductService productService;

    private final PersonService personService;

    private final OrderService orderService;

    @Value("${upload.path}")
    private String uploadPath;

    private final CategoryRepository categoryRepository;

    public AdminController(ProductService productService, PersonService personService, OrderService orderService, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.personService = personService;
        this.orderService = orderService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("admin/product/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryRepository.findAll());
        return "product/addProduct";
    }

    @PostMapping("/admin/product/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one")MultipartFile file_one, @RequestParam("category") int category, Model model) throws IOException {
        Category category_db = (Category) categoryRepository.findById(category).orElseThrow();
        System.out.println(category_db.getName());
        if(bindingResult.hasErrors()){
            model.addAttribute("category", categoryRepository.findAll());
            return "product/addProduct";
        }

        if(file_one != null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            file_one.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);

        }


        productService.saveProduct(product, category_db);
        return "redirect:/admin";
    }


    @GetMapping("/admin")
    public String admin(Model model)
    {
        model.addAttribute("products", productService.getAllProduct());
        return "admin";
    }

    @GetMapping("admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    @GetMapping("admin/product/edit/{id}")
    public String editProduct(Model model, @PathVariable("id") int id){
        model.addAttribute("product", productService.getProductId(id));
        model.addAttribute("category", categoryRepository.findAll());
        return "product/editProduct";


    }

    @PostMapping("admin/product/edit/{id}")
    public String editProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @PathVariable("id") int id, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("category", categoryRepository.findAll());
            return "product/editProduct";
        }
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }

    @GetMapping("admin/users")
    public String user(Model model){
        model.addAttribute("person", personService.getAllPerson());
        return "users";
    }

    @GetMapping("admin/ordersAdm")
    public String order(Model model){
        model.addAttribute("order", orderService.getAllOrder());
        return "ordersAdm";
    }

    @GetMapping("admin/users/edit/{id}")
    public String editPerson(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personService.getPersonId(id));
        return "editPerson";
    }

    @PostMapping("admin/users/edit/{id}")
    public String editPerson(@ModelAttribute("users") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "editPerson";
        }
        personService.editPerson(id, person);
        return "redirect:/admin";
    }

    @GetMapping("/admin/order/edit/{id}")
    public String editOrder(Model model, @PathVariable("id") int id){
        model.addAttribute("order", orderService.getOrderId(id));
        return "editOrder";
    }

    @PostMapping("/admin/order/edit/{id}")
    public String editOrder(@ModelAttribute("ordersAdm") @Valid Order order, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "editOrder";
        }
        orderService.editOrder(id, order);
        return "redirect:/admin";
    }
}
