package com.example.warehouse.repository;

import com.example.warehouse.entity.Input;
import com.example.warehouse.projection.InputProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "input", excerptProjection = InputProjection.class)
public interface InputRepository extends JpaRepository<Input, Long> {

    @Query(value = "SELECT MAX(id) FROM input", nativeQuery = true)
    Long getLastCode();
}
