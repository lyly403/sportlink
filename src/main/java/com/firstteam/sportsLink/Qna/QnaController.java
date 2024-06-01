package com.firstteam.sportsLink.Qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QnaController {

    private final QnaService qnaService;
    private final CommentService commentService;

    @Autowired
    public QnaController(QnaService qnaService, CommentService commentService) {
        this.qnaService = qnaService;
        this.commentService = commentService;
    }

    @PostMapping("/qna/save")
    public String saveQna(@ModelAttribute QnaDTO qnaDTO) {
        qnaService.saveQna(qnaDTO);
        return "redirect:/qna-list"; // 저장 후 목록 페이지로 리다이렉트
    }

    @GetMapping("/qna-list")
    public String qna(Model model, @RequestParam(name = "page", defaultValue = "1") int page,
                      @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequestDTO pageRequest = new PageRequestDTO(page, size);
        List<QnaDTO> inquiries = qnaService.getInquiriesPaged(pageRequest);
        int totalInquiries = qnaService.getTotalInquiryCount();

        int startNumber = (page - 1) * size + 1;

        model.addAttribute("inquiries", inquiries);
        model.addAttribute("totalPages", (int) Math.ceil((double) totalInquiries / size));
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("startNumber", startNumber); // 항목 번호 시작점 추가

        return "qna/qna";
    }

    // 글쓰기 페이지를 반환하는 메서드 추가
    @GetMapping("/qna/new")
    public String newQna(Model model) {
        model.addAttribute("qnaDTO", new QnaDTO());
        return "qna/qna_write"; // qna_write.html로 매핑
    }

    @GetMapping("/qna/new/{product}")
    public String productQna(@PathVariable("product") String product, Model model) {
        model.addAttribute("qnaDTO", new QnaDTO());
        model.addAttribute("product", product);
        return "qna/qna_write"; //
    }

    @GetMapping("/qna_inner/{id}")
    public String qnainner(@PathVariable("id") Long id, Model model) {
        if (id == null) {
            // id가 null이면 404 오류 반환
            return "error/404";
        }

        QnaDTO inquiry = qnaService.getInquiryById(id);
        if (inquiry == null) {
            // 해당 ID에 대한 문의사항이 없으면 404 오류 반환
            return "error/404";
        }

        // 조회수 증가
        qnaService.increaseHit(id);

        // 문의사항과 댓글을 모델에 추가하여 페이지 반환
        List<CommentDTO> comments = commentService.getCommentsByInquiryId(id);
        model.addAttribute("inquiry", inquiry);
        model.addAttribute("comments", comments);
        model.addAttribute("newComment", new CommentDTO());
        model.addAttribute("isAdmin", true);

        return "qna/qna_inner";
    }


    @GetMapping("/editInquiry/{id}")
    public String editInquiryPage(@PathVariable("id") Long id, Model model) {
        QnaDTO inquiry = qnaService.getInquiryById(id);

        if (inquiry == null) {
            return "error/404";
        }

        model.addAttribute("inquiry", inquiry);
        return "qna/qna_edit";
    }

    @PostMapping("/updateInquiry")
    public String updateInquiry(@ModelAttribute("inquiry") QnaDTO inquiry) {
        qnaService.updateInquiry(inquiry);
        return "redirect:/qna_inner/" + inquiry.getId(); // 수정된 결과를 보여줄 페이지로 리다이렉트
    }

    @PostMapping("/deleteInquiry")
    public String deleteInquiry(@RequestParam("id") Long id) {
        // inquiryId를 사용하여 해당 문의사항을 삭제하는 로직을 구현
        qnaService.deleteInquiry(id);
        // 삭제 후 리다이렉트할 URL을 반환합니다. 이 예에서는 메인 페이지로 리다이렉트합니다.
        return "redirect:/";
    }

    @PostMapping("/addComment")
    public String addComment(@ModelAttribute("newComment") CommentDTO newComment) {
        Long inquiryId = newComment.getInquiryId(); // 댓글이 속한 문의사항의 ID

        // 댓글 저장
        commentService.saveComment(newComment);

        // 해당 문의사항 페이지로 리다이렉트
        return "redirect:/qna_inner/" + inquiryId;
    }

    @PostMapping("/deleteComment")
    public String deleteComment(@RequestParam("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/qna-list"; // 댓글이 삭제된 후 리다이렉트할 경로
    }
}

