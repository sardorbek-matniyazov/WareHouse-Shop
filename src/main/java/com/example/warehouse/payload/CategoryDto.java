package com.example.warehouse.payload;

import lombok.Data;

@Data
public class CategoryDto {
    private String name;
    private Long parentCategoryId;
}
