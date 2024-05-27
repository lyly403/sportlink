package com.firstteam.sportsLink.Controller;

import com.firstteam.sportsLink.Product.ProductEntity;
import com.firstteam.sportsLink.Product.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String qna(HttpServletRequest request, Model model, RedirectAttributes ra) {
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");
        if (userid != null) {
            return "qna/qna_write";
        } else {
            model.addAttribute("msg", "로그인 후 이용해주세요");
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }
    }


    @GetMapping("/")
    public String firstPage(Model model) {
        List<ProductEntity> allProduct = productService.findAllProduct();
        model.addAttribute("products", allProduct ); // "products"라는 이름으로 제품 목록 추가
        return "index";
    }
}
