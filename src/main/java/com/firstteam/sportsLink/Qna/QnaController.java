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
        return "redirect:/qna-list";
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
        model.addAttribute("startNumber", startNumber);

        return "qna/qna";
    }

    @GetMapping("/qna/new")
    public String newQna(Model model) {
        model.addAttribute("qnaDTO", new QnaDTO());
        return "qna/qna_write";
    }

    @GetMapping("/qna/new/{product}")
    public String productQna(@PathVariable("product") String product, Model model) {
        model.addAttribute("qnaDTO", new QnaDTO());
        model.addAttribute("product", product);
        return "qna/qna_write";
    }

    @GetMapping("/qna_inner/{id}")
    public String qnainner(@PathVariable("id") Long id, Model model) {
        if (id == null) {
            return "error/404";
        }

        QnaDTO inquiry = qnaService.getInquiryById(id);
        if (inquiry == null) {
            return "error/404";
        }

        qnaService.increaseHit(id);

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
        return "redirect:/qna_inner/" + inquiry.getId();
    }

    @PostMapping("/deleteInquiry")
    public String deleteInquiry(@RequestParam("id") Long id) {
        qnaService.deleteInquiry(id);
        return "redirect:/";
    }

    @PostMapping("/addComment")
    public String addComment(@ModelAttribute("newComment") CommentDTO newComment) {
        Long inquiryId = newComment.getInquiryId();
        commentService.saveComment(newComment);
        return "redirect:/qna_inner/" + inquiryId;
    }

    @PostMapping("/deleteComment")
    public String deleteComment(@RequestParam("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/qna-list";
    }
}
