package com.example.warehouse.repository;

import com.example.warehouse.entity.Users;
import com.example.warehouse.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "user", excerptProjection = UserProjection.class)
public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByPhoneNumber(String number);
    boolean existsByPhoneNumberAndId(String number, Long id);

    @Query(value = "SELECT MAX(id) FROM users", nativeQuery = true)
    Long getLastCode();
}
