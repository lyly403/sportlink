package com.firstteam.sportsLink.Purchase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @PostMapping("/purchase")
    public String purchaseProduct(@ModelAttribute PurchaseDTO purchaseDTO) {
        PurchaseEntity purchaseEntity = purchaseDTO.toEntity();
        purchaseService.savePurchase(purchaseEntity);
        return "redirect:/ticket";
    }

    @GetMapping("/order")
    public String getOrderHistory(Model model, @SessionAttribute("id") String userid) {
        List<PurchaseEntity> orderHistory = purchaseService.getOrderHistoryByUserId(userid);
        System.out.println(orderHistory.toString());
        model.addAttribute("orderHistory", orderHistory);
        return "/user/order"; // Thymeleaf 템플릿 파일 이름
    }

}

