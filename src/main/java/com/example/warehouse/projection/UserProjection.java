package com.example.warehouse.projection;

import com.example.warehouse.entity.Warehouse;

import java.util.Set;

public interface UserProjection {
    Long getId();
    String getFirstName();
    String getLastName();
    String getPhoneNumber();
    String getCode();
    boolean isActive();
    Set<Warehouse> getWarehouses();

}
