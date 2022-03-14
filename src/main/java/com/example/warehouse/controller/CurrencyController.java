package com.example.warehouse.controller;

import com.example.warehouse.entity.Currency;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/currency")
@AllArgsConstructor
public class CurrencyController {
    private final CurrencyService service;

    @GetMapping(value = "/all")
    public List<Currency> getAll(){
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Currency get(@PathVariable Long id){
        return service.get(id);
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody Currency dto){
        return service.add(dto);
    }

    @PutMapping(value = "/{id}")
    public Result edit(@RequestBody Currency dto, @PathVariable Long id){
        return service.edit(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id){
        return service.delete(id);
    }
}