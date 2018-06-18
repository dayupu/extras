package org.dayup.avatar.model.define.page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class PageQuery implements Serializable {

    private static final long serialVersionUID = -3055286445722618459L;

    private PageInfo page;
    private OperateSearch search;

    public PageRequest page() {
        if (page == null) {
            page = new PageInfo();
        }
        return PageRequest.of(this.page.getNum() - 1, this.page.getSize());
    }

    public PageRequest page(Sort sort) {
        if (page == null) {
            page = new PageInfo();
        }
        return PageRequest.of(this.page.getNum() - 1, this.page.getSize(), sort);
    }

    public OperateSearch getSearch() {
        return search;
    }

    public void setSearch(OperateSearch search) {
        this.search = search;
    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }
}
