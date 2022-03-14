package com.example.warehouse.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OutputDto {
    private Long houseId;
    private Long clientId;
    private Long currencyId;
    private List<ForListProduct> products;
}
