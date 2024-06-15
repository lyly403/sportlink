package com.firstteam.sportsLink.Purchase;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public String purchaseProduct(@SessionAttribute("userid") String userid, @ModelAttribute PurchaseDTO purchaseDTO) {
        if(userid != null) {
            PurchaseEntity purchaseEntity = purchaseDTO.toEntity();
            purchaseService.savePurchase(purchaseEntity);
            return "redirect:/ticket";
        } else {
            return "user/login";
        }
    }

    @GetMapping("/order")
    public String getOrderHistory(Model model, @SessionAttribute("userid") String userid) {
        List<PurchaseEntity> orderHistory = purchaseService.getOrderHistoryByUserId(userid);
        System.out.println(orderHistory.toString());
        model.addAttribute("orderHistory", orderHistory);
        return "user/order"; // Thymeleaf 템플릿 파일 이름
    }

    @PostMapping("/order/delete/{orderNo}")
    public String deleteProduct(@PathVariable("orderNo") Long orderNo) {
        purchaseService.deleteProductById(orderNo);
        return "redirect:order";
    }
}

