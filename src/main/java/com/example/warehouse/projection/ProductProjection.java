package com.example.warehouse.projection;

import com.example.warehouse.entity.Category;
import com.example.warehouse.entity.Measurement;
import org.springframework.beans.factory.annotation.Value;

public interface ProductProjection {
    Long getId();
    String getName();
    @Value("#{target.category.toString()}")
    Category getCategory();
    String getCode();
    @Value("#{target.measurement.toString()}")
    Measurement getMeasurement();
}
