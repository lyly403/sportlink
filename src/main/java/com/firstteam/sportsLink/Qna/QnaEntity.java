package com.firstteam.sportsLink.Qna;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "inquiries")
@Getter
@Setter
public class QnaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private LocalDate date;
    private String content;
    private int hit;

    // 생성자, getter 및 setter 생략
}