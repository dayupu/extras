package org.dayup.fun.aide.web.controller.tools;

import org.dayup.fun.aide.web.config.AjaxModelAndView;
import org.dayup.fun.aide.web.config.pjax.EnableAjaxPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/tools/timestamp")
public class TimestampController {

    @EnableAjaxPage
    @RequestMapping
    public ModelAndView compare(HttpServletRequest request, Model model) {
        return new AjaxModelAndView("tools/index", "tools/content/timestamp");
    }

}
