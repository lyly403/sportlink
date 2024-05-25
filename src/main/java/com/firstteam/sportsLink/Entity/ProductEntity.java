package com.firstteam.sportsLink.Entity;

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
<<<<<<< Updated upstream:src/main/java/com/firstteam/sportsLink/Entity/ProductEntity.java
<<<<<<< Updated upstream:src/main/java/com/firstteam/sportsLink/Entity/ProductEntity.java
<<<<<<< Updated upstream:src/main/java/com/firstteam/sportsLink/Entity/ProductEntity.java
    private double latitude;
    private double longitude;
}
=======
    private Double latitude;
    private Double longitude;
}
>>>>>>> Stashed changes:src/main/java/com/firstteam/sportsLink/Product/ProductEntity.java
=======
    private Double latitude;
    private Double longitude;
}
>>>>>>> Stashed changes:src/main/java/com/firstteam/sportsLink/Product/ProductEntity.java
=======
    private Double latitude;
    private Double longitude;
}
>>>>>>> Stashed changes:src/main/java/com/firstteam/sportsLink/Product/ProductEntity.java
