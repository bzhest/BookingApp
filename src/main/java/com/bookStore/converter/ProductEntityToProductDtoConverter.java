package com.bookStore.converter;

import com.bookStore.dto.ProductDto;
import com.bookStore.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityToProductDtoConverter {

    public ProductDto convert(Product product) {
        if (null != product) {
            ProductDto dto = new ProductDto();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            dto.setAuthor(product.getAuthor());
            dto.setPrice(product.getPrice());
            dto.setImagePath(product.getImagePath());
            return dto;
        }
        return null;
    }
}
