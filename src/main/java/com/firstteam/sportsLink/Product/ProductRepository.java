package com.firstteam.sportsLink.Product;

import com.firstteam.sportsLink.Product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
