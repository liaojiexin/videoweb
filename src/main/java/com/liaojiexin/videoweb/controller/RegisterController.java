package com.liaojiexin.videoweb.controller;

import com.liaojiexin.videoweb.entity.RespCode;
import com.liaojiexin.videoweb.entity.RespEntity;
import com.liaojiexin.videoweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController     //返回json值
public class RegisterController {       //注册控制层
    @Autowired
    private UserService userService;

//    在参数里面@RequestBoby只能有一个，请求参数永远都是一个，因为一个request中只包含一个request body.
//    理解了这个，就会明白Spring MVC不支持多个@RequestBody。
//    @RequestParam则可以多个，两者的差别：https://blog.csdn.net/weixin_38004638/article/details/99655322
    @PostMapping(value = "/user/register")  //和前端form表单的action相对应或者ajax对应,consumes指定处理请求的提交内容类型（Content-Type）
    public RespEntity register(@RequestParam("username") String username, @RequestParam("password") String password,
                               @RequestParam("repassword") String repassword,@RequestParam("email") String email,
                               Date birthday, Map<String ,Object> map)
    {
        boolean is=userService.isUserRegister(username,password,repassword,email,birthday,map);
        String msgregister=(String)map.get("msgregister");
        if(is==true){
            return new RespEntity(RespCode.SUCCESS);
        }
        else{
            return new RespEntity(RespCode.ERROR,msgregister);
        }
    }
}


/*spring boot+ajax前后端分离参考：
*https://blog.csdn.net/bat_xu/article/details/82597149
*https://www.cnblogs.com/yelao/p/9835707.html
*https://blog.csdn.net/ththcc/article/details/81870702
* */