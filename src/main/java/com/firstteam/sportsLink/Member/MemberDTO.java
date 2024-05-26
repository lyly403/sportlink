package com.firstteam.sportsLink.Member;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class MemberDTO {
    private String id; // 홈페이지 가입은생성값
    private String user_name; // 홈페이지 > 이름 || 네이버,카카오 > 닉네임 id={profile_nickname})
    private String pw; // 홈페이지 > 비밀번호 || 네이버,카카오 > 없음
    private String email; // 홈페이지 > 이메일 || 네이버,카카오 > 이메일 id={account_email})
    private LocalDate create_date; // 생성 날짜
    private String role; // 권한(기본값 : null, 관리자 : admin)

    // DTO에서 필요한 정보만 Entity로 만들기 위해 만든 매서드
    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .id(this.id)
                .user_name(this.user_name)
                .pw(this.pw)
                .email(this.email)
                .create_date(this.create_date)
                .role(this.role)
                .build();
    }
}

