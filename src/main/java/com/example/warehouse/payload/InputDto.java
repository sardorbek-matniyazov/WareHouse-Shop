package com.example.warehouse.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InputDto {
    private Long houseId;
    private Long currencyId;
    private Long supplierId;
    private List<ForListProduct> products;
}
