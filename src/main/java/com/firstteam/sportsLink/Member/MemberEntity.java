package com.firstteam.sportsLink.Member;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_info")
public class MemberEntity {
    @Id
    @Column(name = "user_id", length = 191)
    private String userid; // 홈페이지 가입은생성값 || 네이버,카카오 > {provider + "_" + providerId}
    @Column(name = "user_name")
    private String username; // 홈페이지 > 이름 || 네이버,카카오 > 닉네임 id={profile_nickname})
    private String pw; // 홈페이지 > 비밀번호 || 네이버,카카오 > 없음
    private String email; // 홈페이지 > 이메일 || 네이버,카카오 > 이메일 id={account_email})
    @Column(name = "mobile")
    private String mobile; // 전화번호
    private LocalDate create_date; // 생성 날짜

    private String role; //권한(User,Admin)
}
