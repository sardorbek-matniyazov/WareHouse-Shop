package com.example.warehouse.repository;

import com.example.warehouse.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByPhoneNumber(String number);
    boolean existsByPhoneNumberAndId(String number, Long id);

    @Query(value = "SELECT MAX(id) FROM users", nativeQuery = true)
    Long getLastCode();
}
