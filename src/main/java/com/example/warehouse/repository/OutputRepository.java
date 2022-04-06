package com.example.warehouse.repository;

import com.example.warehouse.entity.Output;
import com.example.warehouse.projection.OutputProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "output", excerptProjection = OutputProjection.class)
public interface OutputRepository extends JpaRepository<Output, Long> {

    @Query(value = "SELECT MAX(id) FROM output", nativeQuery = true)
    Long getLastCode();
}
