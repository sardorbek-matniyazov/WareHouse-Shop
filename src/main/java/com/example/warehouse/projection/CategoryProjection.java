package com.example.warehouse.projection;

import com.example.warehouse.entity.Category;

public interface CategoryProjection {
    String getId();
    String getName();
    Category getParentCategory();
}
