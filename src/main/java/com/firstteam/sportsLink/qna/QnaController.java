package com.firstteam.sportsLink.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QnaController {

    private final QnaService qnaService;

    @Autowired
    public QnaController(QnaService qnaService) {
        this.qnaService = qnaService;
    }

    @PostMapping("/qna/save")
    public String saveQna(@ModelAttribute QnaDTO qnaDTO) {
        qnaService.saveQna(qnaDTO);
        return "redirect:/qna-list"; // 저장 후 목록 페이지로 리다이렉트
    }


    @GetMapping("/qna-list")
    public String qna(Model model) {
        // 서비스를 통해 모든 문의사항을 가져옵니다.
        List<QnaDTO> inquiries = qnaService.getAllInquiries();

        // 모델에 문의사항 목록을 추가합니다.
        model.addAttribute("inquiries", inquiries);

        // qna.html 파일을 반환합니다.
        return "qna/qna";
    }

    // 추가: 상세 페이지로 이동하는 핸들러
    @GetMapping("/qna-detail/{id}")
    public String qnaDetail(@PathVariable("id") Long id, Model model) {
        QnaDTO inquiry = qnaService.getInquiryById(id);
        model.addAttribute("inquiry", inquiry);
        return "qna/qna-detail";
    }
}
