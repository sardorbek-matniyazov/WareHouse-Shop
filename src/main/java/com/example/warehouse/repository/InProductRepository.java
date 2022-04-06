package com.example.warehouse.repository;

import com.example.warehouse.entity.InProduct;
import com.example.warehouse.projection.InProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource(path = "inProduct", excerptProjection = InProductProjection.class)
public interface InProductRepository extends JpaRepository<InProduct, Long> {
    @Modifying
    @Transactional
    void deleteAllByInputId(Long input_id);

    List<InProduct> findAllByInputId(Long input_id);
}
