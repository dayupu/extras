package org.dayup.fun.aide.web.controller.tools;

import org.dayup.fun.aide.web.config.AjaxModelAndView;
import org.dayup.fun.aide.web.config.pjax.EnableAjaxPage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

//@Controller
@RequestMapping("/tools/home")
public class HomeController {

    @EnableAjaxPage
    @RequestMapping
    public ModelAndView code(HttpServletRequest request, Model model) {
        return new AjaxModelAndView("tools/index", "tools/home");
    }


}
