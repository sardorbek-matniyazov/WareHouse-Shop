package com.example.warehouse.repository;

import com.example.warehouse.entity.Supplier;
import com.example.warehouse.projection.SupplierProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "supplier", excerptProjection = SupplierProjection.class)
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsByPhoneNumberAndId(String phoneNumber, Long id);
    boolean existsByPhoneNumber(String phoneNumber);
}
