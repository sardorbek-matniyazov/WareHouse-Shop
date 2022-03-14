package com.example.warehouse.repository;

import com.example.warehouse.entity.Input;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InputRepository extends JpaRepository<Input, Long> {

    @Query(value = "SELECT MAX(id) FROM input", nativeQuery = true)
    Long getLastCode();
}
