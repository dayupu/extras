package org.dayup.fun.aide.web.controller.tools;

import org.dayup.fun.aide.web.config.AjaxModelAndView;
import org.dayup.fun.aide.web.config.pjax.AjaxPageEnable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/tools/format")
public class FormatController {

    @AjaxPageEnable
    @RequestMapping("json")
    public ModelAndView formatJson(HttpServletRequest request, Model model) {
        return new AjaxModelAndView("tools", "biz/tools/format/jsonFmt.html");
    }

    @AjaxPageEnable
    @RequestMapping("sql")
    public ModelAndView formatSql(HttpServletRequest request, Model model) {
        return new AjaxModelAndView("tools", "biz/tools/format/sqlFmt.html");
    }

    @AjaxPageEnable
    @RequestMapping("xml")
    public ModelAndView formatXml(HttpServletRequest request, Model model) {
        return new AjaxModelAndView("tools", "biz/tools/format/xmlFmt.html");
    }
}
