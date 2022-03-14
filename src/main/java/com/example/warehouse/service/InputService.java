package com.example.warehouse.service;

import com.example.warehouse.entity.InProduct;
import com.example.warehouse.entity.Input;
import com.example.warehouse.payload.ForListProduct;
import com.example.warehouse.payload.InputDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InputService {

    private final InputRepository repo;
    private final InProductRepository inProductRepository;
    private final WarehouseRepository warehouseRepository;
    private final SupplierRepository supplierRepository;
    private final CurrencyRepository currencyRepository;
    private final ProductRepository productRepository;

    public List<Input> getAll() {
        return repo.findAll();
    }

    public Input get(Long id) {
        Optional<Input> byId = repo.findById(id);
        return byId.orElseGet(Input::new);
    }

    public Result add(InputDto dto) {
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
        if (!supplierRepository.existsById(dto.getSupplierId()))
            return Result.builder()
                    .message("there isn't any supplier")
                    .status(true)
                    .build();
        if (!currencyRepository.existsById(dto.getCurrencyId()))
            return Result.builder()
                    .message("there isn't any currency")
                    .status(true)
                    .build();

        //TODO: {factory, code, date}, supplier, currency, house, <input productdan jalg'aw karak>
        Input input = repo.save(Input.builder()
                .currency(
                        currencyRepository.getById(
                                dto.getCurrencyId()
                        ))
                .warehouse(
                        warehouseRepository.getById(
                                dto.getHouseId()
                        ))
                .supplier(
                        supplierRepository.getById(
                                dto.getSupplierId()
                        ))
                .code(getCode())
                .factureNumber(getNumber())
                .build());

        setInputProducts(input.getId(), dto.getProducts());
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

    private void setInputProducts(Long id, List<ForListProduct> products) {
        // TODO: amount, price, expire date, input, product
        inProductRepository.deleteAllByInputId(id);
        products.forEach(product -> {
            if (productRepository.existsById(product.getProductId())) {
                inProductRepository.save(
                        InProduct.builder()
                                .input(
                                        repo.getById(id))
                                .product(
                                        productRepository.getById(
                                                product.getProductId()
                                        ))
                                .amount(
                                        product.getAmount())
                                .price(
                                        product.getPrice())
                                .expireDate(
                                        new Date(new Date().getTime() + product.getExpireDay() * 24 * 60 * 60 * 1000))
                                .build()

                );
            }
        });
    }

    public Result edit(Long id, InputDto dto) {
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
        if (!supplierRepository.existsById(dto.getSupplierId()))
            return Result.builder()
                    .message("there isn't any supplier")
                    .status(true)
                    .build();
        if (!currencyRepository.existsById(dto.getCurrencyId()))
            return Result.builder()
                    .message("there isn't any currency")
                    .status(true)
                    .build();

        //TODO: {factory, code, date}, supplier, currency, house, <input productdan jalg'aw karak>
        Input input = repo.save(Input.builder()
                .id(id)
                .currency(
                        currencyRepository.getById(
                                dto.getCurrencyId()
                        ))
                .warehouse(
                        warehouseRepository.getById(
                                dto.getHouseId()
                        ))
                .supplier(
                        supplierRepository.getById(
                                dto.getSupplierId()
                        ))
                .code(getCode())
                .factureNumber(getNumber())
                .build());

        setInputProducts(input.getId(), dto.getProducts());
        return Result.builder()
                .message("the history has successfully edited")
                .status(true)
                .build();
    }

    public Result delete(Long id) {
        if (!repo.existsById(id))
            return Result.builder()
                    .message("there isn't any input data!")
                    .status(false)
                    .build();

        try {
            inProductRepository.deleteAllByInputId(id);
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

    public List<InProduct> getProducts(Long id) {
        Optional<Input> byId = repo.findById(id);
        if (byId.isPresent()){
            Input input = byId.get();
            return inProductRepository.findAllByInputId(input.getId());
        }
        return null;
    }
}
