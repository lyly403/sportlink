package com.firstteam.sportsLink.Controller;

import com.firstteam.sportsLink.DTO.ProductDTO;
import com.firstteam.sportsLink.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/ticket/ticket_write")
    public String showProductForm(Model model) {
        model.addAttribute("productDto", new ProductDTO());
        return "ticket/ticket_write";
    }

    @PostMapping("/product/new")
    public String createProduct(ProductDTO productDTO) {
        productService.saveProduct(productDTO);
        return "redirect:/product/new?success";
    }
}
