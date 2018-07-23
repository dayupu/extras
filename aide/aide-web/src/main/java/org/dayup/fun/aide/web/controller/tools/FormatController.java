package org.dayup.fun.aide.web.controller.tools;

import org.dayup.fun.aide.web.config.AjaxModelAndView;
import org.dayup.fun.aide.web.config.pjax.EnableAjaxPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/tools/format")
public class FormatController {

    @EnableAjaxPage
    @RequestMapping("json")
    public ModelAndView formatJson() {
        return new AjaxModelAndView("tools/index", "tools/content/format_json");
    }

    @EnableAjaxPage
    @RequestMapping("xml")
    public ModelAndView formatXml() {
        return new AjaxModelAndView("tools/index", "tools/content/format_xml");
    }

    @EnableAjaxPage
    @RequestMapping("sql")
    public ModelAndView formatSql(HttpServletRequest request, Model model) {
        return new AjaxModelAndView("tools", ajaxPage("sqlFmt.html"));
    }

    private String ajaxPage(String page) {
        return "tools/content/" + page;
    }

}
