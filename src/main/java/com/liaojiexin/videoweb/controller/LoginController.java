package com.liaojiexin.videoweb.controller;

import com.liaojiexin.videoweb.entity.User;
import com.liaojiexin.videoweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {      //处理登录请求 ，登录控制器

    @Autowired
    private UserService userService;

//    @DeleteMapping   和PostMapping用法相似，处理Delete请求
//    @PutMapping      和PostMapping用法相似，处理Put请求
//    @GetMapping      和PostMapping用法相似，处理Get请求

//    @RequestMapping(value = "/user/login",method = RequestMethod.POST)  //post请求方式访问当前路径下/user/login
    @PostMapping(value = "/user/login")  //等于@RequestMapping(value = "/user/login",method = RequestMethod.POST)，value的值是和前端form中th:action相同
    public String login(@RequestParam("username") String username,    //@RequestParam标注的属性没有提交，就会报错
                        @RequestParam("password") String password,      //前端页面input中要写name=“username”和name=“password”
                        Map<String,Object> map,HttpSession session)   //session存登录的用户
    {
        User user = userService.userLogin(username,password);

        if(user != null){                                                  //登录成功
            session.setAttribute("loginUser",user);         //将用户信息放入session
            return "redirect:/personal";
        }
//        if (!StringUtils.isEmpty(username)&& "123456".equals(password)){  //登录成功
//            session.setAttribute("loginUser",username);     //记录登录的用户
//            //防止表单重复提交，可以*重定向*到主页，在MyMvcConfig中写registry.addViewController("/personal").setViewName("personal");addViewController请求，setViewName页面
//              return "redirect:/personal";  //addViewController("/personal")中的“/personal”，*重定向*解决页面样式问题，或者前端页面路径都用绝对路径
////            return "forward:/personal";  //转发到当前项目下的/personal*请求*，（注意：没有像redirect一样在MyMvcConfig中设置的话，就是一个请求，）
////            return "personal";     //跳到到personal.html页面,thymeleaf默认会拼串，classpath:/templates/xxx.html
//        }
        else{       //登录失败
            map.put("msglogin","用户名密码错误,请重新输入！");
            return "login";   //这里不可以重定向，重定向message值取不到
        }
    }



    @RequestMapping(value = "/user/relogin")    //退出登录
    public String relogin(HttpServletRequest request){
        if (request.getSession().getAttribute("loginUser")!=null)
            request.getSession().removeAttribute("loginUser");              //清除用户信息的session
        return "login";
    }
}
