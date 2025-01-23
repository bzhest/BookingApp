package com.bookStore.service;

import com.bookStore.converter.ProductEntityToProductDtoConverter;
import com.bookStore.dto.ProductDto;
import com.bookStore.entity.Product;
import com.bookStore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductEntityToProductDtoConverter productEntityToProductDtoConverter;


    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream().map(productEntityToProductDtoConverter::convert).toList();
    }

    public ProductDto getProductById(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        return productEntityToProductDtoConverter.convert(product);
    }

    public ProductDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setAuthor(productDto.getAuthor());
        product.setImagePath(productDto.getImagePath());
        product.setPrice(productDto.getPrice());
        Product savedProduct = productRepository.save(product);
        return productEntityToProductDtoConverter.convert(savedProduct);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
        log.info("Booking with id '" + id + "'was deleted");
    }
}
