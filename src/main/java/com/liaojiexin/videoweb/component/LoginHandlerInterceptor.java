package com.liaojiexin.videoweb.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//https://www.cnblogs.com/sgh1023/p/10056859.html
public class LoginHandlerInterceptor implements HandlerInterceptor {        //登录拦截器,登录检查，只用登录的用户才能访问个人页面按
                                                                            //要到MyMvcConfig内注册拦截器
    //preHandle：目标执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser==null)
        {   //未登录,并返回到登录页
            request.setAttribute("msglogin","没有权限，请先登录！");
            request.getRequestDispatcher("/login").forward(request,response);  //转发
            return false;
        }else{  //已经登录
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
