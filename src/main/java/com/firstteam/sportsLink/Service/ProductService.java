package com.firstteam.sportsLink.Service;

import com.firstteam.sportsLink.DTO.ProductDto;
import com.firstteam.sportsLink.Entity.ProductEntity;
import com.firstteam.sportsLink.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(ProductEntity product) {
        productRepository.save(product);
    }
}
