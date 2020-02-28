package com.liaojiexin.videoweb.service;


import com.github.pagehelper.PageInfo;
import com.liaojiexin.videoweb.entity.Manage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ManageService {
    boolean isLogin(String mname, String mpassword, HttpSession session); //管理员登录

    Manage selectManager(HttpServletRequest request);//查询管理员个人资料

    boolean updateManager(Manage manager, Map<String, Object> map);//管理员资料修改

    boolean updatePassword(HttpServletRequest request, String oldPassword, String newPassword, String newagPassword, Map<String, Object> map);//修改密码

    PageInfo selectPersonalVideo(String vname, int pageNum, int pageSize); //查出未审核的视频
}
