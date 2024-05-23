package com.firstteam.sportsLink.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Setter
@Getter
public class ProductDTO {
        private String title;
        private LocalDate endDate;
        private String location;
        private BigDecimal price;
        private int count;
        private String category;
        private String description;
        private String imageUrl;

        // Getters and setters
}
