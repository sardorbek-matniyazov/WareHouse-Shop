package com.example.warehouse.repository;

import com.example.warehouse.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsByPhoneNumberAndId(String phoneNumber, Long id);
    boolean existsByPhoneNumber(String phoneNumber);
}
