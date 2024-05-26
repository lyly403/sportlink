package com.firstteam.sportsLink.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CommentDTO> getCommentsByInquiryId(Long inquiryId) {
        String sql = "SELECT * FROM comments WHERE inquiry_id = ?";
        return jdbcTemplate.query(sql, new Object[]{inquiryId}, new BeanPropertyRowMapper<>(CommentDTO.class));
    }

    public void saveComment(CommentDTO commentDTO) {
        String sql = "INSERT INTO comments (inquiry_id, content, author, created_at) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, commentDTO.getInquiryId(), commentDTO.getContent(), commentDTO.getAuthor(), LocalDateTime.now());
    }

    public CommentDTO getCommentById(Long commentId) {
        String sql = "SELECT * FROM comments WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{commentId}, new BeanPropertyRowMapper<>(CommentDTO.class));
    }

    public void deleteComment(Long commentId) {
        String sql = "DELETE FROM comments WHERE id = ?";
        jdbcTemplate.update(sql, commentId);
    }
}
