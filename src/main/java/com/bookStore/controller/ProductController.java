package com.bookStore.controller;

import com.bookStore.dto.ProductDto;
import com.bookStore.entity.Product;
import com.bookStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGER', 'CUSTOMER')")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products")
    @PreAuthorize("hasAnyRole('MANAGER', 'CUSTOMER')")
    public String products(Model model) {
        model.addAttribute("products", getAllProducts());
        return "test";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ProductDto getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public ProductDto updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        productDto.setId(id);
        return productService.saveProduct(productDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

}
