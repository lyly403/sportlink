package com.firstteam.sportsLink.Purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    public void savePurchase(PurchaseEntity purchase) {
        purchaseRepository.save(purchase);
    }

    public void deleteProductById(Long id) {
        purchaseRepository.deleteById(id);
    }

    public List<PurchaseEntity> getOrderHistoryByUserId(String userid) {
        return purchaseRepository.findByUserid(userid);
    }
}
