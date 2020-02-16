package com.liaojiexin.videoweb.service;

import com.github.pagehelper.PageInfo;
import com.liaojiexin.videoweb.entity.User;
import com.liaojiexin.videoweb.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

public interface UserService{
    User userLogin(String username, String password);       //登录
    boolean isUserRegister(String username,String password,String repassword,String email,Date birthday, Map<String ,Object> map);     //注册
    User selectUser(HttpServletRequest request);   //个人中心 用户所有信息
    boolean updateUser(User user,Map<String,Object> map);      //修改个人资料
    boolean updatePassword(HttpServletRequest request,String oldPassword,String newPassword,String newagPassword,Map<String,Object> map);       //修改密码
    PageInfo selectPersonalVideo(Integer uid,String vname, Integer pageNum, Integer pageSize); //个人中心查出视频管理的视频
    boolean deletePersonalVideo(Integer vid);   //个人中心视频管理 删除视频
    PageInfo selectLikeVideo(Integer uid,String vname, Integer pageNum, Integer pageSize); //个人中心喜欢的视频查询
    boolean uploadingVideo(HttpServletRequest request, String vname, String vtag, String introduce, Map<String,Object> map, MultipartFile file);//上传视频
}
