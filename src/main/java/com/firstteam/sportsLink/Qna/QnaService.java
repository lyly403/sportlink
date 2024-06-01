package com.firstteam.sportsLink.Qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class QnaService {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QnaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveQna(QnaDTO qnaDTO) {
        // qnaDTO를 이용하여 DB에 저장하는 로직을 구현
        LocalDate date = qnaDTO.getDate();
        if (date == null) {
            date = LocalDate.now(); // 현재 날짜로 설정
        }
        String sql = "INSERT INTO inquiries (title, author, date, content, hit) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, qnaDTO.getTitle(), qnaDTO.getAuthor(), date, qnaDTO.getContent(), 0);
    }

    public void increaseHit(Long id) {
        String sql = "UPDATE inquiries SET hit = hit + 1 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // 데이터베이스에서 모든 문의사항을 가져오는 메서드
    public List<QnaDTO> getAllInquiries() {
        // SQL 쿼리를 작성합니다.
        String sql = "SELECT * FROM inquiries ORDER BY id DESC";
        // 쿼리를 실행하여 결과를 QnaDTO 객체로 매핑하여 리스트로 반환합니다.
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(QnaDTO.class));
    }

    public QnaDTO getInquiryById(Long id) {
        String sql = "SELECT * FROM inquiries WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(QnaDTO.class));
        } catch (EmptyResultDataAccessException e) {
            return null; // 결과가 없을 경우 null 반환
        }
    }

    public void editInquiry(QnaDTO qnaDTO) {
        // qnaDTO를 이용하여 DB에서 해당 문의사항을 수정하는 로직을 구현
        LocalDate date = qnaDTO.getDate();
        if (date == null) {
            date = LocalDate.now(); // 현재 날짜로 설정
        }
        String sql = "UPDATE inquiries SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, qnaDTO.getTitle(), qnaDTO.getContent(), qnaDTO.getId());
    }

    public void updateInquiry(QnaDTO qnaDTO) {
        String sql = "UPDATE inquiries SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, qnaDTO.getTitle(), qnaDTO.getContent(), qnaDTO.getId());
    }

    public void deleteInquiry(Long id) {
        String sql = "DELETE FROM inquiries WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<QnaDTO> getInquiriesPaged(PageRequestDTO pageRequest) {
        int offset = (pageRequest.getPage() - 1) * pageRequest.getSize();
        String sql = "SELECT * FROM inquiries ORDER BY id DESC LIMIT ?, ?";
        return jdbcTemplate.query(sql, new Object[]{offset, pageRequest.getSize()}, new BeanPropertyRowMapper<>(QnaDTO.class));
    }

    public int getTotalInquiryCount() {
        String sql = "SELECT COUNT(*) FROM inquiries";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}

