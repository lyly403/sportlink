package com.firstteam.sportsLink.Qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final QnaRepository qnaRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, QnaRepository qnaRepository) {
        this.commentRepository = commentRepository;
        this.qnaRepository = qnaRepository;
    }

    public List<CommentDTO> getCommentsByInquiryId(Long inquiryId) {
        return commentRepository.findByInquiryId(inquiryId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void saveComment(CommentDTO commentDTO) {
        CommentEntity commentEntity = toEntity(commentDTO);
        commentEntity.setCreatedAt(LocalDateTime.now());
        commentRepository.save(commentEntity);
    }

    public CommentDTO getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .map(this::toDto)
                .orElse(null);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    private CommentDTO toDto(CommentEntity entity) {
        return new CommentDTO(entity.getId(), entity.getInquiry().getId(), entity.getContent(), entity.getAuthor(), entity.getCreatedAt().toString());
    }

    private CommentEntity toEntity(CommentDTO dto) {
        CommentEntity entity = new CommentEntity();
        entity.setId(dto.getId());
        entity.setInquiry(qnaRepository.findById(dto.getInquiryId()).orElse(null));
        entity.setContent(dto.getContent());
        entity.setAuthor(dto.getAuthor());
        entity.setCreatedAt(LocalDateTime.parse(dto.getCreatedAt()));
        return entity;
    }
}
