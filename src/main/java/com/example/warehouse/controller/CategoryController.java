package com.example.warehouse.controller;

import com.example.warehouse.payload.CategoryDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping(value = "/add")
    public Result add(@RequestBody CategoryDto dto){
        return service.add(dto);
    }

    @PutMapping(value = "/{id}")
    public Result edit(@Valid @RequestBody CategoryDto dto, @PathVariable Long id){
        return service.edit(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id){
        return service.delete(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> getException(
            MethodArgumentNotValidException ex) {
        return handleValidationExceptions(ex);
    }

    public static Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
