package com.liaojiexin.videoweb.component;


import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

//可以在连接上携带区域信息  国际化
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {           //解析区域信息
        String l = request.getParameter("l");   //l值与login.html页面中英文设置中的值一样
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(l)){    //如果l不为空，及前端页面l有传值进来
            String[] split = l.split("_");      //en_US,zh_CN
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
