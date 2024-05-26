package com.firstteam.sportsLink.Member;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

import java.time.LocalDate;
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
            member.setCreate_date(LocalDate.now());
            memberRepository.save(member.toEntity());
            return "회원가입 성공";
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
            session.setAttribute("role", check.get().getRole());
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
//    // [ 내정보 ]
//    public String member_info(String id,Model model) {
//        String sessionId = (String)session.getAttribute("id");
//        if(sessionId == null)
//            return "로그인 후 이용하세요.";
//
//        if(!sessionId.equals("admin") && !sessionId.equals(id)) {
//            return "본인의 아이디를 선택하세요.";
//        }
//        MemberEntity member_info = memberRepository.getById(id);
//        model.addAttribute("member", member_info);
//        return "회원 검색 완료";
//    }
    public MemberDTO getMemberById(String id) {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);
        if (memberEntityOptional.isPresent()) {
            MemberEntity memberEntity = memberEntityOptional.get();
            return convertToDTO(memberEntity);
        } else {
            throw new RuntimeException("아이디를 찾을 수 없습니다 : " + id);
        }
    }

    private MemberDTO convertToDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setUser_name(memberEntity.getUser_name());
        memberDTO.setEmail(memberEntity.getEmail());
        // 필요한 경우 다른 필드도 추가
        return memberDTO;
    }

    // [ 회원 중복확인 ]
    @Transactional
    public MemberEntity registerNewMember(MemberDTO memberDTO) {
        MemberEntity memberEntity = memberDTO.toEntity();
        return memberRepository.save(memberEntity);
    }

    public boolean isMemberExists(String id) {
        return memberRepository.existsById(id);
    }
}
