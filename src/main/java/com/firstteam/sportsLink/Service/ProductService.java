package com.firstteam.sportsLink.Service;

import com.firstteam.sportsLink.DTO.ProductDTO;
import com.firstteam.sportsLink.Entity.ProductEntity;
import com.firstteam.sportsLink.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductEntity saveProduct(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setTitle(productDTO.getTitle());
        productEntity.setEndDate(productDTO.getEndDate());
        productEntity.setLocation(productDTO.getLocation());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setCount(productDTO.getCount());
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setImageUrl(productDTO.getImageUrl());
        return productRepository.save(productEntity);
    }
}
