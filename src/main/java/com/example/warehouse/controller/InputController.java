package com.example.warehouse.controller;

import com.example.warehouse.entity.Category;
import com.example.warehouse.entity.InProduct;
import com.example.warehouse.entity.Input;
import com.example.warehouse.payload.CategoryDto;
import com.example.warehouse.payload.InputDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.InputService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/input")
@AllArgsConstructor
public class InputController {
    private final InputService service;

    @GetMapping(value = "/all")
    public List<Input> getAll(){
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Input get(@PathVariable Long id){
        return service.get(id);
    }

    @GetMapping(value = "/products/{id}")
    public List<InProduct> getProducts(@PathVariable Long id){
        return service.getProducts(id);
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody InputDto dto){
        return service.add(dto);
    }

    @PutMapping(value = "/{id}")
    public Result edit(@RequestBody InputDto dto, @PathVariable Long id){
        return service.edit(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id){
        return service.delete(id);
    }
}
