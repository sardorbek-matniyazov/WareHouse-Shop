package com.example.warehouse.controller;

import com.example.warehouse.payload.InputDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.InputService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.example.warehouse.controller.CategoryController.handleValidationExceptions;

@RestController
@RequestMapping(value = "/input")
@AllArgsConstructor
public class InputController {
    private final InputService service;

    @PostMapping(value = "/add")
    public HttpEntity<Result> add(@Valid @RequestBody InputDto dto){
        Result add = service.add(dto);
        return add.isStatus() ? ResponseEntity.ok(add):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(add);
    }

    @PutMapping(value = "/{id}")
    public HttpEntity<Result> edit(@PathVariable Long id,@Valid @RequestBody InputDto dto){
        Result edit = service.edit(id, dto);
        return edit.isStatus() ? ResponseEntity.ok(edit):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(edit);
    }

    @DeleteMapping(value = "{id}")
    public HttpEntity<Result> delete(@PathVariable Long id){
        Result delete = service.delete(id);
        return delete.isStatus() ? ResponseEntity.ok(delete):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(delete);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> getException(
            MethodArgumentNotValidException ex) {
        return handleValidationExceptions(ex);
    }
}
