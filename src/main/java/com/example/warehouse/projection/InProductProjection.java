package com.example.warehouse.projection;

import com.example.warehouse.entity.Input;
import com.example.warehouse.entity.Product;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface InProductProjection {
    Long getId();
    @Value("#{target.product.toString()}")
    Product getProduct();
    Double getAmount();
    Double getPrice();
    Date getExpireDate();
    @Value("#{target.input.toString()}")
    Input getInput();
}
