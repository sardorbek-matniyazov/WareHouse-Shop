package com.example.warehouse.service;

import com.example.warehouse.entity.Users;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.payload.ForListDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.payload.UserDto;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repo;
    private final WarehouseRepository warehouseRepository;

    public Result add(UserDto dto) {
        if (repo.existsByPhoneNumber(dto.getNumber()))
            return Result.builder()
                    .message("the number has already used")
                    .status(false)
                    .build();
        if (!dto.getPassword().equals(dto.getPrePassword()))
            return Result.builder()
                    .message("the password doesn't equal pre password")
                    .status(false)
                    .build();

        repo.save(
                Users.builder()
                        .active(true)
                        .code(getCode())
                        .firstname(dto.getFirstname())
                        .lastname(dto.getLastname())
                        .password(dto.getPassword())
                        .phoneNumber(dto.getNumber())
                        .warehouses(getWarehouses(
                                dto.getHouseId()
                        ))
                        .build()
        );

        return Result.builder()
                .message("the User has successfully created")
                .status(true)
                .build();
    }

    private Set<Warehouse> getWarehouses(List<ForListDto> houseId) {
        Set<Warehouse> warehouses = new HashSet<>();
        houseId.forEach(ob -> {
            if (warehouseRepository.existsById(ob.getId()))
                warehouses.add(warehouseRepository.getById(ob.getId()));
        });
        return warehouses;
    }

    private String getCode() {
        return String.format("00%d", repo.getLastCode() + 1);
    }

    public Result edit(Long id, UserDto dto) {
        if (!repo.existsById(id))
            return Result.builder()
                    .message("there isn't any user")
                    .status(false)
                    .build();

        if (!repo.existsByPhoneNumberAndId(dto.getNumber(), id))
            return Result.builder()
                    .message("the number has already used")
                    .status(false)
                    .build();
        if (!dto.getPassword().equals(dto.getPrePassword()))
            return Result.builder()
                    .message("the password doesn't equal pre password")
                    .status(false)
                    .build();

        repo.save(
                Users.builder()
                        .id(id)
                        .active(true)
                        .code(getCode())
                        .firstname(dto.getFirstname())
                        .lastname(dto.getLastname())
                        .password(dto.getPassword())
                        .phoneNumber(dto.getNumber())
                        .warehouses(getWarehouses(
                                dto.getHouseId()
                        ))
                        .build()
        );


        return Result.builder()
                .message("the User has successfully edited")
                .status(true)
                .build();
    }

    public Result delete(Long id) {
        try {
            repo.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
            Users byId = repo.getById(id);
            byId.setActive(false);
            repo.save(byId);
            return Result.builder()
                    .message("the product hasn't deleted, but its status was changed to false instead of true")
                    .status(true)
                    .build();
        }
        return Result.builder()
                .message("the User has successfully deleted")
                .status(true)
                .build();
    }
}