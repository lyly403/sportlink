package com.firstteam.sportsLink.Purchase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity,Long> {
    List<PurchaseEntity> findByUserid(String userid);
}
