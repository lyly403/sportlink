package com.firstteam.sportsLink.Member;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {
    @Autowired private MemberService service;
    @Autowired private HttpSession session;

    // [ 회원가입 ]
    @GetMapping("/signup")
    public String signup() {
        return "/user/signup";  // signup.html 파일을 반환
    }

    @PostMapping("/signupProc")
    public String signupProc(MemberDTO member, Model model, RedirectAttributes ra) {
        String msg = service.signupProc(member);
        if(msg.equals("회원 등록 완료")) {
            ra.addFlashAttribute("msg", msg);
            return "redirect:/signup";
        } else if (msg.equals("회원 등록을 다시 시도하세요")) {
            ra.addFlashAttribute("alert", "회원 등록을 다시 시도하세요");
            return "/user/signup";
        }
        model.addAttribute("msg", msg);
        return "/user/signup";
    }

    // [ 로그인 ]
    @GetMapping("/login")
    public String login() {
        return "/login/login";  // signup.html 파일을 반환
    }

    @PostMapping("/loginProc")
    public String loginProc(String id, String pw, MemberDTO member, Model model, RedirectAttributes ra) {
        String msg = service.loginProc(id, pw);
        if(msg.equals("로그인 성공")) {
            ra.addFlashAttribute("msg", msg);
            return "redirect:index";
        }
        model.addAttribute("msg", msg);
        return "login/login";
    }

    @RequestMapping("/logout")
    public String logout(RedirectAttributes ra) {
        session.invalidate();
        // Session 정보 초기화
        ra.addFlashAttribute("msg", "로그 아웃");
        //kakaoService.unlink();
        return "redirect:index";
    }
}
