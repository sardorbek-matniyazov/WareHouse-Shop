package com.example.warehouse.controller;

import com.example.warehouse.entity.Measurement;
import com.example.warehouse.payload.CategoryDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.MeasurementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
@AllArgsConstructor
public class MeasurementController {
    private final MeasurementService service;

    @GetMapping(value = "/all")
    public List<Measurement> getAll(){
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Measurement get(@PathVariable Long id){
        return service.get(id);
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody Measurement measurement){
        return service.add(measurement);
    }

    @PutMapping(value = "/{id}")
    public Result edit(@RequestBody Measurement dto, @PathVariable Long id){
        return service.edit(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id){
        return service.delete(id);
    }
}
