package org.dayup.avatar.model.define;

import org.springframework.data.domain.PageRequest;

import java.io.Serializable;

public class PageQuery implements Serializable {

    private static final long serialVersionUID = -3055286445722618459L;

    private int total = 0;
    private int pageNum = 1;
    private int pageSize = 10;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public PageRequest page() {
        return PageRequest.of(this.pageNum - 1, this.pageSize);
    }
}
