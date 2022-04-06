package com.example.warehouse.repository;

import com.example.warehouse.entity.Currency;
import com.example.warehouse.projection.CurrencyProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "currency", excerptProjection = CurrencyProjection.class)
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    boolean existsByName(String name);
}
