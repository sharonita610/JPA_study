package com.study.jpa.chap05_practice.dto;

import com.study.jpa.chap05_practice.entity.Post;
import lombok.*;
import org.springframework.data.domain.Page;

@Setter
@Getter
@ToString
public class PageResponseDTO<T> {

    private int startPage;

    private int endPage;

    private int currentPage;

    private boolean prev;

    private boolean next;

    private int totalCount;

    // 한 페이지에 배치할 페이지 수 (1~10 / /11~20)
    public static final int PAGE_COUNT = 10;

    // 다른 곳에서도 페이징을 사용하려면 <page> 로 사용하면 안되고 generic type 으로 사용해야한다
    public PageResponseDTO(Page<T> pageData) {

        this.totalCount = (int) pageData.getTotalElements();
        this.currentPage = pageData.getPageable().getPageNumber() + 1;
        this.endPage = (int) (Math.ceil((double) currentPage / PAGE_COUNT) * PAGE_COUNT);
        this.startPage = endPage - PAGE_COUNT + 1;

        int realEnd = pageData.getTotalPages();

        if (realEnd < this.endPage) this.endPage = realEnd;

        this.prev = startPage > 1;
        this.next = endPage < realEnd;

    }
}
