package com.firstteam.sportsLink.qna;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaRepository extends JpaRepository<QnaDTO, Long> {
    // 기타 메서드 정의
}
