package com.example.warehouse.repository;

import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.projection.WarehouseProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "house", excerptProjection = WarehouseProjection.class)
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    boolean existsByName(String name);
}
