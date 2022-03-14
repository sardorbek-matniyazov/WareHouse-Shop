package com.example.warehouse.repository;

import com.example.warehouse.entity.InProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface InProductRepository extends JpaRepository<InProduct, Long> {
    @Modifying
    @Transactional
    void deleteAllByInputId(Long input_id);

    List<InProduct> findAllByInputId(Long input_id);
}
