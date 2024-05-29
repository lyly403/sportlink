package com.firstteam.sportsLink.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    String uploadDirectory = "src/main/resources/static/image";

    public void saveProduct(ProductEntity product) {
        productRepository.save(product);
    }

    public ProductEntity findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
    }
    public ProductEntity findProductByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    public List<ProductEntity> findAllProduct() {
        return productRepository.findAll();
    }

    public List<ProductEntity> findViewingTickets() {
        return productRepository.findByProducttype("Viewingticket");
    }
    public List<ProductEntity> findActivityTickets() {
        return productRepository.findByProducttype("Activity");
    }

    public Page<ProductEntity> findViewingTickets(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return productRepository.findByProducttype("Viewingticket", pageable);
    }
    public Page<ProductEntity> findActivityTickets(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        return productRepository.findByProducttype("Activity", PageRequest.of(page, size));
    }

    public void updateProduct(Long id, ProductDTO productDTO) {
        ProductEntity product = productRepository.findById(id).orElse(null);
        if (product != null) {
            if (productDTO.getTitle() != null) product.setTitle(productDTO.getTitle());
            if (productDTO.getEndDate() != null) product.setEndDate(productDTO.getEndDate());
            if (productDTO.getLocation() != null) product.setLocation(productDTO.getLocation());
            if (productDTO.getPrice() != null) product.setPrice(productDTO.getPrice());
            if (productDTO.getCategory() != null) product.setCategory(productDTO.getCategory());
            if (productDTO.getDescription() != null) product.setDescription(productDTO.getDescription());
            if (productDTO.getLatitude() != null) product.setLatitude(productDTO.getLatitude());
            if (productDTO.getLongitude() != null) product.setLongitude(productDTO.getLongitude());
            if (productDTO.getProducttype() != null) product.setProducttype(productDTO.getProducttype());
            if (productDTO.getImageUrl() != null) product.setImageUrl(productDTO.getImageUrl());

            productRepository.save(product);
        }
    }

    // 삭제 기능 추가
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}