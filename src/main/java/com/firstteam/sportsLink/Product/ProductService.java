package com.firstteam.sportsLink.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.io.IOException;
import java.nio.file.Files;


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

    //    내가 추가한 부분
    public void updateProduct(Long id, ProductDTO productDTO) {
        ProductEntity product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setTitle(productDTO.getTitle());
            product.setEndDate(productDTO.getEndDate());
            product.setLocation(productDTO.getLocation());
            product.setPrice(productDTO.getPrice());
            product.setCategory(productDTO.getCategory());
            product.setDescription(productDTO.getDescription());
            product.setLatitude(productDTO.getLatitude());
            product.setLongitude(productDTO.getLongitude());
            product.setProducttype(productDTO.getProducttype());
            productRepository.save(product);
        }
    }

    // 삭제 기능 추가
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}