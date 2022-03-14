package com.example.warehouse.service;

import com.example.warehouse.entity.Category;
import com.example.warehouse.payload.CategoryDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository repo;

    public Result add(CategoryDto dto) {
        if (
                repo.existsByNameAndParentCategoryId(
                        dto.getName(),
                        dto.getParentCategoryId()
                )
        ) return Result.builder()
                .message("the category has already created")
                .status(false)
                .build();

        Category category = new Category();
        if (setMethod(dto, category)) return Result.builder()
                .message("there is not parent category")
                .status(false)
                .build();

        return Result.builder()
                .message("the category successfully saved")
                .status(true)
                .build();
    }

    public List<Category> getAll() {
        return repo.findAll();
    }

    public Category get(Long id) {
        Optional<Category> byId = repo.findById(id);
        return byId.orElseGet(Category::new);
    }

    public Result edit(Long id, CategoryDto dto) {
        if (!repo.existsById(id)) return Result.builder()
                .message("there is not any category")
                .status(false)
                .build();

        if (
                repo.existsByNameAndParentCategoryId(
                        dto.getName(),
                        dto.getParentCategoryId()
                ) || repo.existsByName(dto.getName())
        ) return Result.builder()
                .message("the category has already created")
                .status(false)
                .build();
        Category byId = repo.getById(id);
        if (setMethod(dto, byId)) return Result.builder()
                .message("there is not parent category")
                .status(false)
                .build();
        return Result.builder()
                .message("the category successfully edited")
                .status(true)
                .build();
    }

    public Result delete(Long id) {
        if (!repo.existsById(id)) return Result.builder()
                .message("there is not any category")
                .status(false)
                .build();
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.builder()
                    .message("you can't delete , cause of there have many active products ")
                    .status(false)
                    .build();
        }

        return Result.builder()
                .message("the category successfully deleted")
                .status(true)
                .build();
    }

    private boolean setMethod(CategoryDto dto, Category byId) {
        byId.setName(dto.getName());
        if (dto.getParentCategoryId() != null)
            if (repo.existsById(dto.getParentCategoryId()))
                byId.setParentCategory(
                        repo.getById(
                                dto.getParentCategoryId()
                        )
                );
            else return true;
        byId.setActive(true);

        repo.save(byId);
        return false;
    }
}
