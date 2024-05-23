package com.firstteam.sportsLink.Repository;

import com.firstteam.sportsLink.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
