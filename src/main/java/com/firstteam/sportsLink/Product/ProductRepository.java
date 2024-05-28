package com.firstteam.sportsLink.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByProducttype(String producttype);
    ProductEntity findByTitle(String title);
    Page<ProductEntity> findByProducttype(String productType, Pageable pageable);
}
