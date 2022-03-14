package com.example.warehouse.controller;

import com.example.warehouse.entity.Product;
import com.example.warehouse.payload.ProductDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping(value = "/all")
    public List<Product> getAll(){
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Product get(@PathVariable Long id){
        return service.get(id);
    }

    @GetMapping(value = "getPhoto/{id}")
    public void getPhoto(HttpServletResponse response, @PathVariable Long id){
        service.getPhoto(response, id);
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody ProductDto dto){
        return service.add(dto);
    }

    @PutMapping(value = "/{id}")
    public Result edit(@RequestBody ProductDto dto, @PathVariable Long id){
        return service.edit(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id){
        return service.delete(id);
    }
}
