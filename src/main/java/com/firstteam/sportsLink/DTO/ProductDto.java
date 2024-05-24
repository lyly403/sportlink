package com.firstteam.sportsLink.DTO;

import com.firstteam.sportsLink.Entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


@Setter
@Getter
public class ProductDto {
        private String title;
        private LocalDate endDate;
        private String location;
        private String price;
        private String category;
        private String description;
        private String imageUrl;
        private String producttype;
        private double latitude;
        private double longitude;

        // Getters and setters

        public ProductEntity toEntity() {
                ProductEntity product = new ProductEntity();
                product.setTitle(this.title);
                product.setEndDate(this.endDate);
                product.setLocation(this.location);
                product.setPrice(this.price);
                product.setCategory(this.category);
                product.setDescription(this.description);
                product.setImageUrl(this.imageUrl);
                product.setLatitude(this.latitude);
                product.setLongitude(this.longitude);
                product.setProducttype(this.producttype);
                return product;
        }
}
