package com.firstteam.sportsLink.Controller;

import com.firstteam.sportsLink.Product.ProductEntity;
import com.firstteam.sportsLink.Product.ProductService;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/index")
    public String index(Model model) {
        List<ProductEntity> allProduct = productService.findAllProduct();
        model.addAttribute("products", allProduct ); // "products"라는 이름으로 제품 목록 추가
        return "index";
    }

//    @GetMapping("/ticket")
//    public String ticket() {
//        return "ticket/ticket";  // ticket.html 파일을 반환
//    }

    @GetMapping("/facility")
    public String facility() {
        return "ticket/activity";  // facility.html 파일을 반환
    }
    @GetMapping("/qna")
    public String qna() {
        return "qna/qna_write";  // Q&A_write.html 파일을 반환
    }
    @GetMapping("/order")
    public String order() {
        return "user/order";  // order.html 파일을 반환
    }
    @GetMapping("/member_list")
    public String memberlist() {
        return "user/member_list";  // order.html 파일을 반환
    }

    @GetMapping("/")
    public String firstPage(Model model) {
        List<ProductEntity> allProduct = productService.findAllProduct();
        model.addAttribute("products", allProduct ); // "products"라는 이름으로 제품 목록 추가
        return "index";
    }
}
