package com.example.warehouse.repository;

import com.example.warehouse.entity.Client;
import com.example.warehouse.projection.ClientProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "client", excerptProjection = ClientProjection.class)
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByNumber(String number);

    Optional<Client> findByNumber(String num);
}
