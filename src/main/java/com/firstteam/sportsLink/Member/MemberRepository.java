package com.firstteam.sportsLink.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    boolean existsByUserid(String userid);
    Page<MemberEntity> findAll(Pageable pageable);
    MemberEntity findByUserid(String userid); // userid 컬럼을 기반으로 사용자 찾기
}