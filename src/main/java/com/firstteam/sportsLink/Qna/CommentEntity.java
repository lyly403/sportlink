package com.firstteam.sportsLink.Qna;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id", nullable = false)
    private QnaEntity inquiry;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String author;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public CommentEntity() {}

    public CommentEntity(Long id, Long inquiryId, String content, String author, LocalDateTime createdAt) {
        this.id = id;
        this.inquiry = new QnaEntity();
        this.inquiry.setId(inquiryId);
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }
}
