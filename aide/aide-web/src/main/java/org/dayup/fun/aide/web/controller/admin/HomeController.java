package org.dayup.fun.aide.web.controller.admin;

import org.dayup.fun.aide.web.config.AjaxModelAndView;
import org.dayup.fun.aide.web.config.pjax.AjaxPageEnable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeController {

    @AjaxPageEnable
    @RequestMapping("jumpA")
    public ModelAndView jumpA(HttpServletRequest request, Model model) {
        return new AjaxModelAndView("index", "biz/admin/a");
    }

    @AjaxPageEnable
    @RequestMapping("jumpB")
    public ModelAndView jumpB(HttpServletRequest request, Model model) {
        return new AjaxModelAndView("index", "biz/admin/b");
    }
}
