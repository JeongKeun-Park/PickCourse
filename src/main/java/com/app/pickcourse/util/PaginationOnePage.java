package com.app.pickcourse.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @ToString
public class PaginationOnePage {
    private int     page;
    private int     pageCount;
    private int     startPage;
    private int     endPage;
    private int     rowCount;
    private int     startRow;
    private int     endRow;
    private int     realEnd;
    private boolean prev;
    private boolean next;

    public PaginationOnePage() {
        this.page = 1;
    }

    public void create(int total) {
        this.rowCount = 5;
        this.pageCount = 1;

        this.endPage = (int)(Math.ceil(this.page / (double)pageCount)) * pageCount;
        this.startPage = endPage - (pageCount - 1);
        this.realEnd = (int)Math.ceil(total / (double)rowCount);
        // 검색창의 기존 검색 내용을 수정하고 page를 클릭했을 때, 조건에 맞는 데이타가 적을 경우 문제 발생 25.03.15 조승찬
        this.startPage = this.startPage > this.realEnd ? this.realEnd : this.startPage;
        this.startPage = this.startPage == 0 ? 1 : this.startPage;
        // 검색창의 기존 검색 내용을 수정하고 page를 클릭했을 때, 조건에 맞는 데이타가 적을 경우 문제 발생 25.03.15 조승찬
        this.endPage = this.endPage > realEnd ? realEnd : endPage;
        // this.endPage가 0일 때 1로 세트하므로 그 전으로 이동 25.03.16 조승찬
        this.prev = startPage > 1;
        this.next = realEnd != endPage;
        // this.endPage가 0일 때 1로 세트하므로 그 전으로 이동 25.03.16 조승찬
        this.endPage = this.endPage == 0 ? 1 : this.endPage;

        this.endRow = rowCount * this.page;
        this.startRow = endRow - (rowCount - 1);
    }
}
