package com.app.pickcourse.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter @ToString
public class QuestionPagination {
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

    public QuestionPagination() {
        this.page = 1;
    }

    public void create(int total) {
        this.rowCount = 1;
        this.pageCount = 1;

        this.endPage = (int)(Math.ceil(this.page / (double)pageCount)) * pageCount;
        this.startPage = endPage - (pageCount - 1);
        this.realEnd = (int)Math.ceil(total / (double)rowCount);
        this.endPage = this.endPage > realEnd ? realEnd : endPage;
        this.endPage = this.endPage == 0 ? 1 : this.endPage;

        this.endRow = rowCount * this.page;
        this.startRow = endRow - (rowCount - 1);
        this.prev = startPage > 1;
        this.next = realEnd != endPage;
    }
}
