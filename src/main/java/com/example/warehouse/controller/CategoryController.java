package com.example.warehouse.controller;

import com.example.warehouse.entity.Category;
import com.example.warehouse.payload.CategoryDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping(value = "/all")
    public List<Category> getAll(){
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Category get(@PathVariable Long id){
        return service.get(id);
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody CategoryDto dto){
        return service.add(dto);
    }

    @PutMapping(value = "/{id}")
    public Result edit(@RequestBody CategoryDto dto, @PathVariable Long id){
        return service.edit(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id){
        return service.delete(id);
    }
}
