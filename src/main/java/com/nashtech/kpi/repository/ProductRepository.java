package com.nashtech.kpi.repository;

import com.nashtech.kpi.dto.ProductDTO;
import com.nashtech.kpi.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    Optional<ProductDTO> findByProductName(String productName);

    List<ProductEntity> findByOutOfStock(boolean outOfStock);

}
