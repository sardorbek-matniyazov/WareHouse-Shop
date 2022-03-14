package com.example.warehouse.service;

import com.example.warehouse.entity.Currency;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.CurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CurrencyService {
    private final CurrencyRepository repo;

    public List<Currency> getAll() {
        return repo.findAll();
    }

    public Currency get(Long id) {
        Optional<Currency> byId = repo.findById(id);
        return byId.orElseGet(Currency::new);
    }

    public Result add(Currency dto) {
        if (repo.existsByName(dto.getName()))
            return Result.builder()
                    .message("the currency has already created")
                    .status(false)
                    .build();

        repo.save(new Currency(dto.getName(), true));

        return Result.builder()
                .message("the currency has successfully edited")
                .status(true)
                .build();
    }

    public Result edit(Long id, Currency dto) {
        if (!repo.existsById(id))
            Result.builder()
                    .message("there isn't any currency")
                    .status(false)
                    .build();
        if (repo.existsByName(dto.getName()))
            return Result.builder()
                    .message("the currency has already created")
                    .status(false)
                    .build();

        repo.save(new Currency(id, dto.getName(), true));

        return Result.builder()
                .message("the currency has successfully edited")
                .status(true)
                .build();
    }

    public Result delete(Long id) {
        if (!repo.existsById(id))
            Result.builder()
                    .message("there isn't any currency")
                    .status(false)
                    .build();
        try {
            repo.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
            Currency byId = repo.getById(id);
            byId.setActive(false);
            repo.save(byId);
            return Result.builder()
                    .message("the currency hasn't deleted, but its status was changed to false instead of true")
                    .status(true)
                    .build();
        }
        return Result.builder()
                .message("the currency has successfully deleted")
                .status(true)
                .build();
    }
}
