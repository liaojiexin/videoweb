package com.liaojiexin.videoweb.config;

import com.liaojiexin.videoweb.component.LoginHandlerInterceptor;
import com.liaojiexin.videoweb.component.LoginManageHandlerInterceptor;
import com.liaojiexin.videoweb.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

@Configuration  //指明当前类是一个配置类；就是来替代之前的Spring配置文件
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // super.addViewControllers(registry);
        //浏览器发送 /atguigu 请求来到 success;和MyController中的操作功能相同
        registry.addViewController("/success").setViewName("success");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {     //注册拦截器
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/personal");
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/user/relogin");
        registry.addInterceptor(new LoginManageHandlerInterceptor()).addPathPatterns("/manage");
    }

    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器
    public WebMvcConfigurer webMvcConfigurerAdapter() {
        WebMvcConfigurer adapter = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //addViewController为请求，setViewName为路径（spring boot设定为templates包下的路径，
                // 如registry.addViewController("/").setViewName("index");为发送“/”请求时，会跳到templates/index.html路径的页面 ）
                registry.addViewController("/").setViewName("index");               //主页
                registry.addViewController("/index.html").setViewName("index");     //主页
                registry.addViewController("/login").setViewName("login");          //登录
                registry.addViewController("/register").setViewName("register");    //注册
                registry.addViewController("/archive").setViewName("archive");      //浏览
                registry.addViewController("/personal").setViewName("personal");    //个人主页
                registry.addViewController("/single").setViewName("single");        //播放
                registry.addViewController("/contact").setViewName("contact");      //联系
                registry.addViewController("/managelogin").setViewName("managelogin");      //管理员登录
                registry.addViewController("/manage").setViewName("manage");      //视频审核
            }
        };
        return adapter;
    }
    @Bean
    public LocaleResolver localeResolver(){   //配置自己的区域解析器
        return new MyLocaleResolver();
    }

    /**
     * 中文乱码解决
     */
//    @Bean
//    public HttpMessageConverter<String> responseBodyConverter() {
//        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        return converter;
//    }
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        WebMvcConfigurer.super.configureMessageConverters(converters);
//        converters.add(responseBodyConverter());
//    }
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.favorPathExtension(false);
//    }
}