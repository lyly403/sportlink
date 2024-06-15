package com.firstteam.sportsLink.Qna;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QnaDTO {
    private Long id;
    private String title;
    private String author;
    private LocalDate date;
    private String content;
    private int hit;
}
