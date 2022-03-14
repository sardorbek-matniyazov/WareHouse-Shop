package com.example.warehouse.controller;

import com.example.warehouse.entity.InProduct;
import com.example.warehouse.entity.OutProduct;
import com.example.warehouse.entity.Output;
import com.example.warehouse.payload.OutputDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.OutputService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/output")
@AllArgsConstructor
public class OutputController {
    private final OutputService service;

    @GetMapping(value = "/all")
    public List<Output> getAll(){
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Output get(@PathVariable Long id){
        return service.get(id);
    }

    @GetMapping(value = "/products/{id}")
    public List<OutProduct> getProducts(@PathVariable Long id){
        return service.getProducts(id);
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody OutputDto dto){
        return service.add(dto);
    }

    @PutMapping(value = "/{id}")
    public Result edit(@RequestBody OutputDto dto, @PathVariable Long id){
        return service.edit(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id){
        return service.delete(id);
    }
}
