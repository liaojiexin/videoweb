package com.liaojiexin.videoweb.controller;

import com.liaojiexin.videoweb.entity.RespCode;
import com.liaojiexin.videoweb.entity.RespEntity;
import com.liaojiexin.videoweb.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController     //视频审核管理模块
public class ManageController {

    @Autowired
    private ManageService manageService;

    @PostMapping("/managelogin/login")  //管理员登录模块
    public RespEntity register(@RequestParam("mname") String mname,
                               @RequestParam("mpassword") String mpassword,
                               Map<String ,Object> map, HttpSession session)
    {
        boolean is=manageService.isLogin(mname,mpassword,map,session);
        String msgmanagelogin=(String)map.get("msgmanagelogin");
        if(is==true){
            return new RespEntity(RespCode.SUCCESS);
        }
        else{
            return new RespEntity(RespCode.ERROR,msgmanagelogin);
        }
    }

}
