package com.liaojiexin.videoweb.service.impl;

import com.liaojiexin.videoweb.entity.Manage;
import com.liaojiexin.videoweb.mapper.ManageMapper;
import com.liaojiexin.videoweb.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class ManageServiceImpl implements ManageService {
    @Autowired
    private ManageMapper manageMapper;

    @Override   //管理员登录
    public boolean isLogin(String mname, String mpassword, Map<String, Object> map,HttpSession session) {
        Manage m=new Manage();
        m.setMname(mname);
        m.setMpassword(mpassword);
        Manage manage=manageMapper.manageLogin(m);
        if (manage!=null)
        {
            session.setAttribute("loginManager",manage);         //将用户信息放入session
            return true;
        }
        else
        {
            map.put("msgmanagelogin","用户名密码错误,请重新输入！");
            return false;
        }
    }
}
