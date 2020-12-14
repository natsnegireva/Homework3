package com.geekbrains.spring.mvc.controllers;
import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.services.ProductService;
import com.geekbrains.spring.mvc.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(StudentsService studentsService) {
        this.productService = productService;
    }

    @GetMapping
    public String showAllProduct(Model model) {
        List<Product> product = productService.getAllProduct();
        model.addAttribute("product", product);
        return "all_product";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_product";
    }

    @PostMapping("/add")
    public String saveNewProduct (@ModelAttribute Product newProduct) {
        productService.saveOrUpdateProduct(newProduct);
        return "redirect:/product/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "edit_product";
    }

    @PostMapping("/edit")
    public String modifyProduct (@ModelAttribute Product modifiedProduct) {
        productService.saveOrUpdateProduct(modifiedProduct);
        return "redirect:/product/";
    }

    @GetMapping("/json/{id}")
    @ResponseBody
    public Product showJsonProduct(@PathVariable Long id) {
        return productService.findById(id);
    }
}

