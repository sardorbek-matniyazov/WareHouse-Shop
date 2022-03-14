package com.example.warehouse.controller;

import com.example.warehouse.entity.Client;
import com.example.warehouse.entity.Currency;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
@AllArgsConstructor
public class ClientController {
    private final ClientService service;

    @GetMapping(value = "/all")
    public List<Client> getAll(){
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Client get(@PathVariable Long id){
        return service.get(id);
    }

    @PostMapping(value = "/add")
    public Result add(@RequestBody Client dto){
        return service.add(dto);
    }

    @PutMapping(value = "/{id}")
    public Result edit(@RequestBody Client dto, @PathVariable Long id){
        return service.edit(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id){
        return service.delete(id);
    }
}
