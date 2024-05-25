package com.firstteam.sportsLink.Product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name="product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDate endDate;
    private String location;
    private String price;
    private String category;
    private String description;
    private String imageUrl;
    private String producttype;
    private Double latitude;
    private Double longitude;
}