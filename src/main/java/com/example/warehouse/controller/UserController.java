package com.example.warehouse.controller;

import com.example.warehouse.entity.Users;
import com.example.warehouse.payload.Result;
import com.example.warehouse.payload.UserDto;
import com.example.warehouse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping(value = "/all")
    public List<Users> getAll(){
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Users get(@PathVariable Long id){
        return service.get(id);
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody UserDto dto){
        return service.add(dto);
    }

    @PutMapping(value = "/{id}")
    public Result edit(@RequestBody UserDto dto, @PathVariable Long id){
        return service.edit(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id){
        return service.delete(id);
    }
}
