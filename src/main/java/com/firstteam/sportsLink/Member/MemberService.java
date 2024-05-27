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
        if(member.getUserid() == null || member.getUserid().trim().isEmpty()) {
            return "아이디를 입력하세요.";
        }
        if(member.getPw() == null || member.getPw().trim().isEmpty()) {
            return "비밀번호를 입력하세요.";
        }
        if(member.getUsername() == null || member.getUsername().trim().isEmpty()) {
            return "이름을 입력하세요.";
        }

        if(memberRepository.existsByUserid(member.getUserid())) {
            return "이미 사용중인 아이디 입니다.";
        }
        try {
            member.setCreate_date(LocalDate.now());
//            member.setRole("user");
            memberRepository.save(member.toEntity());
            return "회원가입 성공";
        } catch (Exception e) {
            e.printStackTrace(); {
                return "회원 등록을 다시 시도하세요";
            }
        }
    }
    // [ 로그인 ]
    public String loginProc(String userid, String pw) {
        if(userid == null || userid.trim().isEmpty()) {
            return "아이디를 입력하세요.";
        }
        if(pw == null || pw.trim().isEmpty()) {
            return "비밀번호를 입력하세요.";
        }

        Optional<MemberEntity> check = Optional.ofNullable(memberRepository.findByUserid(userid));
        if(check.isPresent() && pw.equals(check.get().getPw())) {
            session.setAttribute("userid", check.get().getUserid());
            session.setAttribute("username", check.get().getUsername());
            session.setAttribute("email", check.get().getEmail());
            session.setAttribute("mobile", check.get().getMobile());
            session.setAttribute("role", check.get().getRole());
            /*
             * session.setAttribute("member", check);
             * ${sessionScope.member.userid}
             * ${sessionScope.member.pw}
             * ${sessionScope.member.userName}
             */
            return "로그인 성공";
        }
        return "아이디 또는 비밀번호를 확인 후 다시 입력하세요.";
    }
    // [ 내정보 ]
    public MemberDTO getMemberByUserid(String userid) {
        Optional<MemberEntity> memberEntityOptional = Optional.ofNullable(memberRepository.findByUserid(userid));
        if (memberEntityOptional.isPresent()) {
            MemberEntity memberEntity = memberEntityOptional.get();
            return convertToDTO(memberEntity);
        } else {
            throw new RuntimeException("아이디를 찾을 수 없습니다 : " + userid);
        }
    }

    private MemberDTO convertToDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserid(memberEntity.getUserid());
        memberDTO.setUsername(memberEntity.getUsername());
        memberDTO.setPw(memberEntity.getPw());
        memberDTO.setEmail(memberEntity.getEmail());
        memberDTO.setMobile(memberEntity.getMobile());
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

    public boolean isMemberExists(String userid) {
        return memberRepository.existsByUserid(userid);
    }


    // [ 회원 목록 페이징 ]
    public Page<MemberDTO> getMemberList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MemberEntity> memberPage = memberRepository.findAll(pageable);
        return memberPage.map(this::convertToDTO);
    }

//    // [ 회원 정보 수정 ]
//    public String member_info_edit(MemberDTO member) {
//        // 세션 아이디를 기반으로 사용자 조회
//        MemberDTO existingMember = getMemberByUserid(member.getUserid());
//
//        if (existingMember != null) {
//            // 사용자가 존재하는 경우 모바일 번호 업데이트
//            existingMember.setMobile(member.getMobile());
//            try {
//                memberRepository.save(existingMember.toEntity());
//                return "회원정보 수정완료.";
//            } catch (Exception e) {
//                e.printStackTrace();
//                return "회원 정보 수정을 다시 시도하세요";
//            }
//        } else {
//            // 사용자가 존재하지 않는 경우
//            throw new RuntimeException("사용자를 찾을 수 없습니다: " + member.getUserid());
//        }
//    }
// [ 회원 정보 수정 ]
    public String member_info_edit(MemberDTO member) {
        // 기존 회원 정보 조회
        MemberDTO existingMember = getMemberByUserid(member.getUserid());

        if (existingMember != null) {
            // 사용자가 존재하는 경우 각 필드를 업데이트
            existingMember.setMobile(member.getMobile());
            if (member.getPw() != null && !member.getPw().isEmpty()) {
                existingMember.setPw(member.getPw());
            }

            try {
                memberRepository.save(existingMember.toEntity());
                return "회원정보 수정완료.";
            } catch (Exception e) {
                e.printStackTrace();
                return "회원 정보 수정을 다시 시도하세요";
            }
        } else {
            // 사용자가 존재하지 않는 경우
            throw new RuntimeException("사용자를 찾을 수 없습니다: " + member.getUserid());
        }
    }
}
