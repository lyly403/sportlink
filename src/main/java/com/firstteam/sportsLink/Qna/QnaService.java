package com.firstteam.sportsLink.Qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QnaService {

    private final QnaRepository qnaRepository;

    @Autowired
    public QnaService(QnaRepository qnaRepository) {
        this.qnaRepository = qnaRepository;
    }

    public void saveQna(QnaDTO qnaDTO) {
        QnaEntity qnaEntity = toEntity(qnaDTO);
        if (qnaEntity.getDate() == null) {
            qnaEntity.setDate(LocalDate.now());
        }
        qnaRepository.save(qnaEntity);
    }

    public void increaseHit(Long id) {
        QnaEntity qnaEntity = qnaRepository.findById(id).orElseThrow(() -> new RuntimeException("Inquiry not found"));
        qnaEntity.setHit(qnaEntity.getHit() + 1);
        qnaRepository.save(qnaEntity);
    }

    public List<QnaDTO> getAllInquiries() {
        return qnaRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public QnaDTO getInquiryById(Long id) {
        return qnaRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    public void editInquiry(QnaDTO qnaDTO) {
        QnaEntity qnaEntity = toEntity(qnaDTO);
        if (qnaEntity.getDate() == null) {
            qnaEntity.setDate(LocalDate.now());
        }
        qnaRepository.save(qnaEntity);
    }

    public void updateInquiry(QnaDTO qnaDTO) {
        QnaEntity qnaEntity = toEntity(qnaDTO);
        qnaRepository.save(qnaEntity);
    }

    public void deleteInquiry(Long id) {
        qnaRepository.deleteById(id);
    }

    public List<QnaDTO> getInquiriesPaged(PageRequestDTO pageRequest) {
        int offset = (pageRequest.getPage() - 1) * pageRequest.getSize();
        return qnaRepository.findAll().stream()
                .skip(offset)
                .limit(pageRequest.getSize())
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public int getTotalInquiryCount() {
        return (int) qnaRepository.count();
    }

    private QnaDTO toDto(QnaEntity entity) {
        QnaDTO dto = new QnaDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setAuthor(entity.getAuthor());
        dto.setDate(entity.getDate());
        dto.setContent(entity.getContent());
        dto.setHit(entity.getHit());
        return dto;
    }

    private QnaEntity toEntity(QnaDTO dto) {
        QnaEntity entity = new QnaEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setAuthor(dto.getAuthor());
        entity.setDate(dto.getDate());
        entity.setContent(dto.getContent());
        entity.setHit(dto.getHit());
        return entity;
    }
}
