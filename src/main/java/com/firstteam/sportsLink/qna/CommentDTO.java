package com.firstteam.sportsLink.qna;

import jakarta.persistence.Entity;

public class CommentDTO {
    private Long id;
    private Long inquiryId;
    private String content;
    private String author;
    private String createdAt;

    // 생성자, Getter 및 Setter 메서드 추가
    public CommentDTO() {}

    public CommentDTO(Long id, Long inquiryId, String content, String author, String createdAt) {
        this.id = id;
        this.inquiryId = inquiryId;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }

    // Getter 및 Setter 메서드 추가
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Long inquiryId) {
        this.inquiryId = inquiryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
