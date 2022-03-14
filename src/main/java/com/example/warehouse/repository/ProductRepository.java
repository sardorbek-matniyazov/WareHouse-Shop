package com.example.warehouse.repository;

import com.example.warehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByNameAndCategoryIdAndPhotoIdAndMeasurementId(String name, Long category_id, Long photo_id, Long measurement_id);
    boolean existsByPhotoId(Long photo_id);
    boolean existsByPhotoIdAndId(Long photo_id, Long id);

    @Query(value = "SELECT MAX(id) FROM Product", nativeQuery = true)
    Long getLastCode();
}
