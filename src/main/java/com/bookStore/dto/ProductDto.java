package com.bookStore.dto;

import lombok.Data;

@Data
public class ProductDto {

    private int id;
    private String name;
    private String description;
    private String author;
    private float price;
    private String imagePath;
}