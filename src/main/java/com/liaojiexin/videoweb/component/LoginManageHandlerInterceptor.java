package com.liaojiexin.videoweb.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginManageHandlerInterceptor implements HandlerInterceptor {        //登录拦截器,登录检查，只用登录的用户才能访问个人页面按
    //要到MyMvcConfig内注册拦截器
    //preHandle：目标执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginManager = request.getSession().getAttribute("loginManager");
        if (loginManager==null)
        {   //未登录,并返回到登录页
            //获取当前的session
//            HttpSession session=request.getSession();
//            session.setAttribute("msgmanagelogin","没有权限，请先登录！");
//            response.sendRedirect("/managelogin");//重定向
            request.setAttribute("msgmanagelogin","没有权限，请先登录！");
            request.getRequestDispatcher("/managelogin").forward(request,response);  //转发
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
