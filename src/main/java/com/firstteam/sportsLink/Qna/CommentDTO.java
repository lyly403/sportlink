package com.firstteam.sportsLink.Qna;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private Long inquiryId;
    private String content;
    private String author;
    private String createdAt;
}
