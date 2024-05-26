package com.firstteam.sportsLink.qna;

public class PageRequestDTO {
    private int page; // 요청한 페이지 번호
    private int size; // 페이지당 아이템 수

    public PageRequestDTO(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
