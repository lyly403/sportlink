package com.firstteam.sportsLink.Controller;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/ticket")
    public String ticket() {
        return "ticket/ticket";  // ticket.html 파일을 반환
    }

    @GetMapping("/facility")
    public String facility() {
        return "facility/facility";  // facility.html 파일을 반환
    }

    @GetMapping("/qna")
    public String qna() {
        return "qna/qna";  // Q&A_write.html 파일을 반환
    }

    @GetMapping("/order")
    public String order() {
        return "user/order";  // order.html 파일을 반환
    }

    @GetMapping("/signup")
    public String signup() {
        return "user/signup";  // signup.html 파일을 반환
    }

    @GetMapping("/login")
    public String login() {
        return "login/login";  // login.html 파일을 반환
    }

}
