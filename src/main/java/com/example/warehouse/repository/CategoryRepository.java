package com.example.warehouse.repository;

import com.example.warehouse.entity.Category;
import com.example.warehouse.projection.CategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.transaction.Transactional;

@RepositoryRestResource(path = "category", excerptProjection = CategoryProjection.class)
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameAndParentCategoryId(String name, Long parentCategory_id);
    boolean existsByName(String name);

    @RestResource(path = "name", rel = "name")
    Category findByName(String name);

    @Modifying
    @Transactional
    void deleteAllByParentCategoryId(Long parentCategory_id);
}
