package com.firstteam.sportsLink.Member;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Member;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    boolean existsById(String id);
    Page<MemberEntity> findAll(Pageable pageable);
}
