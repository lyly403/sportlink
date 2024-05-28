package com.firstteam.sportsLink.Purchase;

import com.firstteam.sportsLink.Member.MemberEntity;
import com.firstteam.sportsLink.Product.ProductEntity;
import com.firstteam.sportsLink.Product.ProductService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@Entity
@Table(name="Purchase")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNo;

    private Long product;
    private String userid;
    private String product_id;
    private Long product_price;
    private Long quantity;
    private LocalDate date;

}
