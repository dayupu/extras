package org.dayup.fun.aide.web.config;

import org.springframework.web.servlet.ModelAndView;

public class AjaxModelAndView extends ModelAndView {

    private String page;
    private String ajaxPage;

    public AjaxModelAndView(String page, String ajaxPage) {
        this.page = page;
        this.ajaxPage = ajaxPage;
    }

    public String getPage() {
        return page;
    }

    public String getAjaxPage() {
        return ajaxPage;
    }
}
