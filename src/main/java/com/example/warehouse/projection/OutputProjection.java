package com.example.warehouse.projection;

import com.example.warehouse.entity.Currency;
import com.example.warehouse.entity.Supplier;
import com.example.warehouse.entity.Warehouse;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;

public interface OutputProjection {
    Long getId();
    Timestamp getTime();
    @Value("#{target.warehouse.toString()}")
    Warehouse getWarehouse();
    @Value("#{target.client.toString()}")
    Supplier getClient();
    @Value("#{target.currency.toString()}")
    Currency getCurrency();
    String getFactureNumber();
    String getCode();
}
