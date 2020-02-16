package com.liaojiexin.videoweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//    @RestController
    @Controller
    public class MyController {

//        @RequestMapping({"/","/index.html"})  //不管访问“localhost：8080/”还是“localhost：8080/index.html”
//        public String index(){                //都会自动到模板引擎内（templates包）找index.html，页面相当于设置了主页，
//            return "index";                   //如果不写这段代码且不在欢迎页面（public包）的话，也会默认其为主页
//        }                                     //和MyMvcConfig中的操作相似

//        @ResponseBody  // @ResponseBody和@Controller包含在@RestController中
//        @RequestMapping("/hello")
//        public String hello() {
//            return "Hello Spring Boot!";
//        }

        //不可以用@RestController，否则会返回success字符串，而不是去resource下的templates找succsee.html文件
        //查出一些数据，在页面展示
//        @RequestMapping("/success")
//        public String success(Map<String,Object> map){
//            map.put("hello","你好");
//            return "success";  //success.html和MyMvcConfig中的操作相似
//        }

//        @RequestMapping("/login")
//        public String login(){
//            return "login";  //login.html和MyMvcConfig中的操作相似
//        }
    }
