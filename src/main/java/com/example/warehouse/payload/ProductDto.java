package com.example.warehouse.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {
    @NotBlank(message = "Product name is required")
    private String name;
    @NotNull(message = "Product category is required")
    private Long categoryId;
    @NotNull(message = "Product photo is required")
    private Long photoId;
    @NotNull(message = "Product measurement is required")
    private Long measurementId;
}
