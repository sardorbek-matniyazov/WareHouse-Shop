package com.example.warehouse.repository;

import com.example.warehouse.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    boolean existsByName(String name);
}
