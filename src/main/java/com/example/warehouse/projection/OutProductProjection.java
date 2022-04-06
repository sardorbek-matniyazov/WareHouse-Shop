package com.example.warehouse.projection;

import com.example.warehouse.entity.*;
import org.springframework.beans.factory.annotation.Value;


public interface OutProductProjection {
    Long getId();
    @Value("#{target.product.toString()}")
    Product getProduct();
    Double getAmount();
    Double getPrice();
    @Value("#{target.output.toString()}")
    Output getOutput();
}
