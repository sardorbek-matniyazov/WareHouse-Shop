package com.example.warehouse.repository;

import com.example.warehouse.entity.Output;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OutputRepository extends JpaRepository<Output, Long> {

    @Query(value = "SELECT MAX(id) FROM output", nativeQuery = true)
    Long getLastCode();
}
