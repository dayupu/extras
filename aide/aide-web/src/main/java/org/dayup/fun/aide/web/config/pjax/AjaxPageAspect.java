package org.dayup.fun.aide.web.config.pjax;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.dayup.fun.aide.web.config.AjaxModelAndView;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class AjaxPageAspect {


    @Pointcut("@annotation(org.dayup.fun.aide.web.config.pjax.AjaxPageEnable)")//指向自定义注解路径
    public void AjaxPagePointcut() {

    }


    @Around("AjaxPagePointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = getRequest();
        Object result = point.proceed();
        if (result instanceof AjaxModelAndView) {
            AjaxModelAndView ajaxModel = (AjaxModelAndView) result;
            if (isPageAjax(request)) {
                ajaxModel.setViewName(ajaxModel.getAjaxPage());
                return ajaxModel;
            }

            ajaxModel.getModel().put("includePage", ajaxModel.getAjaxPage());
            ajaxModel.setViewName(ajaxModel.getPage());
            return ajaxModel;
        }

        return result;
    }


    public boolean isPageAjax(HttpServletRequest request) {
        String pageAjaxHeader = request.getHeader("ajax-page");
        if (pageAjaxHeader == null) {
            return false;
        }
        return "true".equals(pageAjaxHeader);
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    private HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
