package com.example.warehouse.service;

import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class WarehouseService {
    private final WarehouseRepository repo;

    public Result add(Warehouse dto) {
        if (repo.existsByName(dto.getName()))
            return Result.builder()
                    .message("the warehouse is already created")
                    .status(false)
                    .build();

        repo.save(new Warehouse(dto.getName(), true));

        return Result.builder()
                .message("the warehouse has successfully created")
                .status(true)
                .build();
    }

    public Result edit(Long id, Warehouse dto) {
        if (!repo.existsById(id))
            return Result.builder()
                    .message("there is not any warehouse !")
                    .status(false)
                    .build();
        if (repo.existsByName(dto.getName()))
            return Result.builder()
                    .message("the warehouse is already created")
                    .status(false)
                    .build();

        repo.save(new Warehouse(id, dto.getName(), true));

        return Result.builder()
                .message("the warehouse has successfully edited")
                .status(true)
                .build();
    }

    public Result delete(Long id) {
        if (!repo.existsById(id))
            return Result.builder()
                    .message("there is not any warehouse !")
                    .status(false)
                    .build();

        try {
            repo.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
            Warehouse byId = repo.getById(id);
            byId.setActive(false);
            repo.save(byId);
            return Result.builder()
                    .message("the warehouse hasn't deleted, but its status was changed to false instead of true")
                    .status(true)
                    .build();
        }

        return Result.builder()
                .message("the warehouse has successfully deleted")
                .status(true)
                .build();
    }
}
