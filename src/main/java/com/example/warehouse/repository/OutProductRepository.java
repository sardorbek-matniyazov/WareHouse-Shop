package com.example.warehouse.repository;

import com.example.warehouse.entity.OutProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface OutProductRepository extends JpaRepository<OutProduct, Long> {
    @Modifying
    @Transactional
    void deleteAllByOutputId(Long output_id);

    List<OutProduct> findAllByOutputId(Long output_id);
}
