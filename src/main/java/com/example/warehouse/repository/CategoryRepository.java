package com.example.warehouse.repository;

import com.example.warehouse.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameAndParentCategoryId(String name, Long parentCategory_id);
    boolean existsByName(String name);

    @Modifying
    @Transactional
    void deleteAllByParentCategoryId(Long parentCategory_id);
}
