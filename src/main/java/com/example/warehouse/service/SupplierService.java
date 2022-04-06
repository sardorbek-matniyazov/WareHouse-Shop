package com.example.warehouse.service;

import com.example.warehouse.entity.Supplier;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SupplierService {
    private final SupplierRepository repo;

    public Result add(Supplier dto) {
        if (repo.existsByPhoneNumber(dto.getPhoneNumber()))
            return Result.builder()
                    .message("the number has already used")
                    .status(false)
                    .build();

        repo.save(
                new Supplier(
                        dto.getName(),
                        dto.isActive(),
                        dto.getPhoneNumber()
                )
        );

        return Result.builder()
                .message("the supplier successfully saved")
                .status(true)
                .build();
    }

    public Result edit(Long id, Supplier dto) {
        if (!repo.existsById(id))
            return Result.builder()
                    .message("the number has already used")
                    .status(false)
                    .build();
        if (!repo.existsByPhoneNumberAndId(dto.getPhoneNumber(), id))
            return Result.builder()
                    .message("the number has already used")
                    .status(false)
                    .build();
        repo.save(
                new Supplier(
                        id,
                        dto.getName(),
                        dto.isActive(),
                        dto.getPhoneNumber()
                )
        );

        return Result.builder()
                .message("the supplier successfully edited")
                .status(true)
                .build();
    }

    public Result delete(Long id) {
        if (!repo.existsById(id))
            return Result.builder()
                    .message("the number has already used")
                    .status(false)
                    .build();
        try {
            repo.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
            Supplier byId = repo.getById(id);
            byId.setActive(false);
            repo.save(byId);
            return Result.builder()
                    .message("the supplier hasn't deleted, but its status was changed to false instead of true")
                    .status(true)
                    .build();
        }
        return Result.builder()
                .message("the supplier successfully edited")
                .status(true)
                .build();
    }
}
