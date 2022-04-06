package com.example.warehouse.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class OutputDto {
    @NotNull(message = "House cannot be null")
    private Long houseId;
    @NotNull(message = "currency cannot be null")
    private Long currencyId;
    @NotNull(message = "client cannot be null")
    private Long clientId;
    @NotNull(message = "products cannot be null")
    private List<ForListProduct> products;
}
