package com.example.warehouse.controller;

import com.example.warehouse.entity.Client;
import com.example.warehouse.entity.Supplier;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/supplier")
@AllArgsConstructor
public class SupplierController {
    private final SupplierService service;

    @GetMapping(value = "/all")
    public List<Supplier> getAll(){
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Supplier get(@PathVariable Long id){
        return service.get(id);
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody Supplier dto){
        return service.add(dto);
    }

    @PutMapping(value = "/{id}")
    public Result edit(@RequestBody Supplier dto, @PathVariable Long id){
        return service.edit(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id){
        return service.delete(id);
    }
}
