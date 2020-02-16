package com.liaojiexin.videoweb.config;


import com.liaojiexin.videoweb.service.*;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;;

//@EnableWebMvc  开启后会完全接管Spring MVC，也就是要自己写所有配置，而不是用已经配置好的默认配置
@Configuration  //指明当前类是一个配置类；就是来替代之前的Spring配置文件
public class MyAppConfig implements WebMvcConfigurer {   //WebMvcConfigurer接口来扩展SpringMVC功能(crtl+o打开选择重写接口的方法)

    //@Bean来添加组件，相当于<bean></bean>标签
//    @Bean  //将方法的返回值添加到容器中；容器中这个组件的默认id就是方法名
//    public HelloServiceImpl helloService(){
//        return new HelloServiceImpl();
//    }
}
