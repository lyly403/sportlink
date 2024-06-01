package com.firstteam.sportsLink.Qna;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaRepository extends JpaRepository<QnaEntity, Long> {
    // 기타 메서드 정의
}
