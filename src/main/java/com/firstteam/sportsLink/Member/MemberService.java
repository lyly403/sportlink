package com.firstteam.sportsLink.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired private MemberRepository memberRepository;
    @Autowired private HttpSession session;

    // [ 회원가입 ]
    public String signupProc(MemberDTO member) {
        if(member.getId() == null || member.getId().trim().isEmpty()) {
            return "아이디를 입력하세요.";
        }
        if(member.getPw() == null || member.getPw().trim().isEmpty()) {
            return "비밀번호를 입력하세요.";
        }
        if(member.getUser_name() == null || member.getUser_name().trim().isEmpty()) {
            return "이름을 입력하세요.";
        }

        if(memberRepository.existsById(member.getId())) {
            return "이미 사용중인 아이디 입니다.";
        }

        try {
            memberRepository.save(member.toEntity());
            return "회원 등록 완료";
        } catch (Exception e) {
            e.printStackTrace(); {
                return "회원 등록을 다시 시도하세요";
            }
        }
    }
    // [ 로그인 ]
    public String loginProc(String id, String pw) {
        if(id == null || id.trim().isEmpty()) {
            return "아이디를 입력하세요.";
        }
        if(pw == null || pw.trim().isEmpty()) {
            return "비밀번호를 입력하세요.";
        }

        Optional<MemberEntity> check = memberRepository.findById(id);
        if(check.isPresent() && pw.equals(check.get().getPw())) {
            session.setAttribute("id", check.get().getId());
            session.setAttribute("user_name", check.get().getUser_name());
            session.setAttribute("email", check.get().getEmail());
            /*
             * session.setAttribute("member", check);
             * ${sessionScope.member.id}
             * ${sessionScope.member.pw}
             * ${sessionScope.member.userName}
             */
            return "로그인 성공";
        }
        return "아이디 또는 비밀번호를 확인 후 다시 입력하세요.";
    }
}
