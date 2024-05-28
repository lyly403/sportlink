package com.firstteam.sportsLink.Purchase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@ToString
@Getter
@Setter
public class PurchaseDTO {

    private Long orderNo;
    private String userid;
    private String product_id;
    private Long product_price;
    private Long quantity;
    private LocalDate date;
    private Long product;


    public PurchaseEntity toEntity(){
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setOrderNo(this.orderNo);
        purchaseEntity.setUserid(this.userid);
        purchaseEntity.setProduct_id(this.product_id);
        purchaseEntity.setProduct_price(this.product_price);
        purchaseEntity.setQuantity(this.quantity);
        purchaseEntity.setProduct(this.product);
        purchaseEntity.setDate(LocalDate.now());
        return purchaseEntity;
    }
}
