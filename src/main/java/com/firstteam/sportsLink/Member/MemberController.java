package com.firstteam.sportsLink.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {
    @Autowired private MemberService service;
    @Autowired private HttpSession session;
    @Autowired private MemberService memberService;

    // [ 회원가입 ]
    @RequestMapping("/signup")
    public String signup() {
        return "/user/signup";  // signup.html 파일을 반환
    }

    @PostMapping("/signupProc")
    public String signupProc(MemberDTO member, Model model, RedirectAttributes ra) {
        String msg = service.signupProc(member);
        if(msg.equals("회원가입 성공")) {
            ra.addFlashAttribute("msg", msg);
            return "redirect:/index";
        } else if (msg.equals("회원 등록을 다시 시도하세요")) {
            ra.addFlashAttribute("alert", "회원 등록을 다시 시도하세요");
            return "/user/signup";
        }
        model.addAttribute("msg", msg);
        return "/user/signup";
    }

    // [ 로그인 ]
    @RequestMapping("/login")
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
    // [ 로그아웃 ]
    @RequestMapping("/logout")
    public String logout(RedirectAttributes ra) {
        session.invalidate();
        // Session 정보 초기화
        ra.addFlashAttribute("msg", "로그아웃");
        //kakaoService.unlink();
        return "redirect:index";
    }
    // [ 카카오 로그인 ]
    @Autowired private KakaoService kakaoService;
    @RequestMapping("/KakaoLogin")
    public String KakaoLogin(String code, RedirectAttributes ra) {
        System.out.println("code : " + code);
        kakaoService.getAccessToken(code);
        kakaoService.getUserInfo();
        ra.addFlashAttribute("msg", session.getAttribute("role"));
        return "redirect:index";
    }
    // [ 네이버 로그인 ]
    @Autowired private NaverService naverService;
    @RequestMapping("/NaverLogin")
    public String NaverLogin(String code, RedirectAttributes ra) {
        System.out.println("code : " + code);
        naverService.getAccessToken(code);
        naverService.getUserInfo();
        ra.addFlashAttribute("msg", "로그인 성공");
        return "redirect:index";
    }

    // [ 내정보 ]
    @GetMapping("/member_info")
    public String memberInfo(HttpServletRequest request, Model model, RedirectAttributes ra) {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");

        if (id != null) {
            MemberDTO memberDTO = memberService.getMemberById(id);
            model.addAttribute("member", memberDTO);
            return "user/member_info";
        } else {
            ra.addFlashAttribute("msg", "로그인 후 이용해주세요");
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }
    }
}


