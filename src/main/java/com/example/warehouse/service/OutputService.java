package com.example.warehouse.service;

import com.example.warehouse.entity.OutProduct;
import com.example.warehouse.entity.Output;
import com.example.warehouse.payload.ForListProduct;
import com.example.warehouse.payload.OutputDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OutputService {
    private final OutputRepository repo;
    private final OutProductRepository outProductRepository;
    private final WarehouseRepository warehouseRepository;
    private final ClientRepository clientRepository;
    private final CurrencyRepository currencyRepository;
    private final ProductRepository productRepository;

    public Result add(OutputDto dto) {
        if (
                dto.getCurrencyId() == null ||
                        dto.getProducts() == null ||
                        dto.getHouseId() == null
        ) Result.builder()
                .message("Oops, Something went wrong !")
                .status(false)
                .build();

        if (!warehouseRepository.existsById(dto.getHouseId()))
            return Result.builder()
                    .message("there isn't any warehouse")
                    .status(true)
                    .build();
        if (!clientRepository.existsById(dto.getClientId()))
            return Result.builder()
                    .message("there isn't any client")
                    .status(true)
                    .build();
        if (!currencyRepository.existsById(dto.getCurrencyId()))
            return Result.builder()
                    .message("there isn't any currency")
                    .status(true)
                    .build();

        Output output = repo.save(Output.builder()
                .currency(
                        currencyRepository.getById(
                                dto.getCurrencyId()
                        ))
                .warehouse(
                        warehouseRepository.getById(
                                dto.getHouseId()
                        ))
                .client(
                        clientRepository.getById(
                                dto.getClientId()
                        ))
                .code(getCode())
                .time(
                        new Timestamp(new Date().getTime()))
                .factureNumber(getNumber())
                .build());

        setOutputProducts(output.getId(), dto.getProducts());
        return Result.builder()
                .message("saved")
                .status(true)
                .build();
    }

    private String getCode() {
        Long code = repo.getLastCode();
        return String.format("00%d", code == null ? 1 : code + 1);
    }

    private String getNumber() {
        return String.valueOf(System.currentTimeMillis() % 10000000L);
    }

    private void setOutputProducts(Long id, List<ForListProduct> products) {
        // TODO: amount, price, expire date, input, product
        outProductRepository.deleteAllByOutputId(id);
        products.forEach(product -> {
            if (productRepository.existsById(product.getProductId())) {
                outProductRepository.save(
                        OutProduct.builder()
                                .output(
                                        repo.getById(id))
                                .product(
                                        productRepository.getById(
                                                product.getProductId()
                                        ))
                                .amount(
                                        product.getAmount())
                                .price(
                                        product.getPrice())
                                .build()

                );
            }
        });
    }

    public Result edit(Long id, OutputDto dto) {
        if (!repo.existsById(id))
            return Result.builder()
                    .message("there isn't any output data!")
                    .status(false)
                    .build();

        if (
                dto.getCurrencyId() == null ||
                        dto.getProducts() == null ||
                        dto.getHouseId() == null
        ) Result.builder()
                .message("Oops, Something went wrong !")
                .status(false)
                .build();

        if (!warehouseRepository.existsById(dto.getHouseId()))
            return Result.builder()
                    .message("there isn't any warehouse")
                    .status(true)
                    .build();
        if (!clientRepository.existsById(dto.getClientId()))
            return Result.builder()
                    .message("there isn't any supplier")
                    .status(true)
                    .build();
        if (!currencyRepository.existsById(dto.getCurrencyId()))
            return Result.builder()
                    .message("there isn't any currency")
                    .status(true)
                    .build();

        Output output = repo.save(Output.builder()
                        .id(id)
                .currency(
                        currencyRepository.getById(
                                dto.getCurrencyId()
                        ))
                .warehouse(
                        warehouseRepository.getById(
                                dto.getHouseId()
                        ))
                .client(
                        clientRepository.getById(
                                dto.getClientId()
                        ))
                .code(getCode())
                .factureNumber(getNumber())
                .build());

        setOutputProducts(output.getId(), dto.getProducts());
        return Result.builder()
                .message("the history has successfully edited")
                .status(true)
                .build();
    }

    public Result delete(Long id) {
        if (!repo.existsById(id))
            return Result.builder()
                    .message("there isn't any output data!")
                    .status(false)
                    .build();

        try {
            outProductRepository.deleteAllByOutputId(id);
            repo.deleteById(id);
        }catch (Exception e){
            return Result.builder()
                    .message("the history can't delete, because of its history")
                    .status(true)
                    .build();
        }

        return Result.builder()
                .message("the history has successfully deleted")
                .status(true)
                .build();
    }
}
