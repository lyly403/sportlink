package com.firstteam.sportsLink.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        return "user/signup";  // signup.html 파일을 반환
    }

    @PostMapping("/signupProc")
    public String signupProc(MemberDTO member, Model model, RedirectAttributes ra) {
        String msg = service.signupProc(member);
        if(msg.equals("회원가입 성공")) {
            ra.addFlashAttribute("msg", msg);
            return "redirect:index";
        } else if (msg.equals("회원 등록을 다시 시도하세요")) {
            ra.addFlashAttribute("alert", "회원 등록을 다시 시도하세요");
            return "user/signup";
        }
        model.addAttribute("msg", msg);
        return "user/signup";
    }

    // [ 로그인 ]
    @RequestMapping("/login")
    public String login() {
        return "user/login";  // signup.html 파일을 반환
    }

    @PostMapping("/loginProc")
    public String loginProc(@RequestParam("userid") String userid, @RequestParam("pw") String pw, MemberDTO member, Model model, RedirectAttributes ra) {
        String msg = service.loginProc(userid, pw);
        if(msg.equals("로그인 성공")) {
            ra.addFlashAttribute("msg", msg);
            return "redirect:index";
        }
        model.addAttribute("msg", msg);
        return "user/login";
    }
    // [ 로그아웃 ]
    @RequestMapping("/logout")
    public String logout(RedirectAttributes ra) {
        session.invalidate();
        // Session 정보 초기화
        ra.addFlashAttribute("msg", "로그아웃");
        kakaoService.unlink(); // 카카오 회원탈퇴
        return "redirect:index";
    }
    // [ 카카오 로그인 ]
    @Autowired private KakaoService kakaoService;
    @RequestMapping("/KakaoLogin")
    public String KakaoLogin(String code, RedirectAttributes ra) {
        System.out.println("code : " + code);
        kakaoService.getAccessToken(code);
        kakaoService.getUserInfo();
        ra.addFlashAttribute("msg", "로그인 성공");
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
    @GetMapping("/member_info/{userid}")
    public String memberInfo(@PathVariable("userid") String userid,HttpServletRequest request, Model model, RedirectAttributes ra) {
        HttpSession session = request.getSession();
        String sessionuserid = (String) session.getAttribute("userid");
        String role = (String) session.getAttribute("role");
        if (userid == null) {
            ra.addFlashAttribute("msg", "잘못된 사용자 아이디입니다.");
            return "redirect:index";
        }

        if (sessionuserid == null ) {
            ra.addFlashAttribute("msg", "로그인 후 이용해주세요.");
            return "redirect:login";
        }

        if (sessionuserid.equals(userid)) {
            MemberDTO memberDTO = memberService.getMemberByUserid(userid);
            model.addAttribute("member", memberDTO);
            return "user/member_info";
        } else if ("admin".equals(role)) {
            model.addAttribute("member", memberService.getMemberByUserid(userid));
            return "user/member_info";
        } else {
            ra.addFlashAttribute("msg", "접근 권한이 없습니다.");
            return "redirect:index";
        }
    }

    // [ 회원리스트 ]
    @GetMapping("/member_list")
    public String listMembers(HttpServletRequest request, RedirectAttributes ra, Model model, @RequestParam(defaultValue = "0") int page) {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if (role.equals("admin")) {
            int size = 10; // 페이지당 항목 수
            Page<MemberDTO> memberPage = memberService.getMemberList(page, size);
            model.addAttribute("memberPage", memberPage);
            return "user/member_list";
        }
        else {
            ra.addFlashAttribute("msg", "접근권한이 없습니다.");
            return "redirect:/index";
        }
    }
    // [ 회원정보 수정 ]
    @PostMapping("/member_info_edit/{userid}")
    public String memberInfoEdit(@PathVariable("userid") String userid ,MemberDTO member, Model model, RedirectAttributes ra, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionuserid = (String) session.getAttribute("userid");
        String role = (String) session.getAttribute("role");

        if (sessionuserid != null) {
            member.setUserid(userid); // 세션 아이디 설정
            String msg = service.member_info_edit(member);
            if (msg.equals("회원정보 수정완료.")) {
                ra.addFlashAttribute("msg", msg);
                return "redirect:member_info/"+userid;
            } else if (msg.equals("회원 정보 수정을 다시 시도하세요")) {
                ra.addFlashAttribute("msg", "회원 정보 수정을 다시 시도하세요");
                return "redirect:member_info/"+userid;
            }
        } else {
            ra.addFlashAttribute("msg", "로그인 후 이용해주세요.");
            return "redirect:login"; // 로그인 페이지로 리다이렉트
        }
        model.addAttribute("msg", "회원 정보 수정을 다시 시도하세요");
        return "redirect:member_info"+userid;
    }
}


