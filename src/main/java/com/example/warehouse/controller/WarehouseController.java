package com.example.warehouse.controller;

import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/house")
@AllArgsConstructor
public class WarehouseController {
    private final WarehouseService service;

    @GetMapping(value = "/all")
    public List<Warehouse> getAll(){
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Warehouse get(@PathVariable Long id){
        return service.get(id);
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody Warehouse dto){
        return service.add(dto);
    }

    @PutMapping(value = "/{id}")
    public Result edit(@RequestBody Warehouse dto, @PathVariable Long id){
        return service.edit(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id){
        return service.delete(id);
    }
}
