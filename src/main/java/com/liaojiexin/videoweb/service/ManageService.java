package com.liaojiexin.videoweb.service;


import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ManageService {
    boolean isLogin(String mname, String mpassword, Map<String, Object> map, HttpSession session); //管理员登录
}
