package com.example.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ForListProduct {
    private Long productId;
    private Double amount;
    private Double price;
    private Long expireDay;
}
