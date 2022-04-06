package com.example.warehouse.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryDto {
    @NotBlank(message = "Category name is required")
    private String name;
    private Long parentCategoryId;
}
