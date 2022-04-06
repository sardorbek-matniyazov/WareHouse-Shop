package com.example.warehouse.repository;

import com.example.warehouse.entity.OutProduct;
import com.example.warehouse.projection.OutProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;
@RepositoryRestResource(path = "outProduct", excerptProjection = OutProductProjection.class)
public interface OutProductRepository extends JpaRepository<OutProduct, Long> {
    @Modifying
    @Transactional
    void deleteAllByOutputId(Long output_id);

    List<OutProduct> findAllByOutputId(Long output_id);
}
