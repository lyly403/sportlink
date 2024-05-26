package com.firstteam.sportsLink.Member;

import com.firstteam.sportsLink.qna.PageRequestDTO;
import com.firstteam.sportsLink.qna.QnaDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

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
    // [ 내정보 ]
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
        memberDTO.setCreate_date(memberEntity.getCreate_date());
        memberDTO.setRole(memberEntity.getRole());
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


    // [ 회원 목록 페이징 ]
    public Page<MemberDTO> getMemberList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MemberEntity> memberPage = memberRepository.findAll(pageable);
        return memberPage.map(this::convertToDTO);
    }
}
