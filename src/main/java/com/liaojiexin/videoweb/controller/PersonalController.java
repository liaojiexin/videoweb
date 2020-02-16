package com.liaojiexin.videoweb.controller;

import com.github.pagehelper.PageInfo;
import com.liaojiexin.videoweb.entity.RespCode;
import com.liaojiexin.videoweb.entity.RespEntity;
import com.liaojiexin.videoweb.entity.User;
import com.liaojiexin.videoweb.entity.Video;
import com.liaojiexin.videoweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PersonalController {       //个人中心的控制层
    @Autowired
    private UserService userService;

    @PostMapping("/user/selectuser")        //查出个人所有信息
    public RespEntity selectUser(HttpServletRequest request){
        User user=userService.selectUser(request);
        return new RespEntity(RespCode.SUCCESS,user);
    }

    @PutMapping("/user/updateuser")        //修改个人资料
    public RespEntity updateUser(HttpServletRequest request,
                                 @RequestParam("username") String username,
                                 @RequestParam("email") String email,
                                 @RequestParam("birthday") String date)
    {
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        User user=new User();
        user.setUid(loginUser.getUid());
        user.setUsername(username);
        user.setEmail(email);
        try{     //前端传过来为字符，把它转换为时间格式,再存到user内
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            Date birthday =simpleDateFormat.parse(date);
            user.setBirthday(birthday);
        }catch (ParseException e){
            e.printStackTrace();
            System.out.println("转换异常");
        }
        Map<String,Object> map=new HashMap<>();
        boolean updateuser=userService.updateUser(user,map);
        if(updateuser==true)
        {
            return new RespEntity(RespCode.SUCCESS);
        }else{
            String msgupdateuser=(String)map.get("msgupdateuser");
            return new RespEntity(RespCode.ERROR,msgupdateuser);
        }
    }

    @PostMapping("/user/updatepassword")    //修改密码
    public RespEntity updatePassword(HttpServletRequest request,
                                     @RequestParam("oldPassword") String oldPassword,
                                     @RequestParam("newPassword") String newPassword,
                                     @RequestParam("newagPassword") String newagPassword)
    {
        Map<String,Object> map=new HashMap<>();
        boolean updatepassword=userService.updatePassword(request,oldPassword,newPassword,newagPassword,map);
        if(updatepassword==true){
            return new RespEntity(RespCode.SUCCESS);
        }else{
            String msgpassword=(String)map.get("msgpassword");
            return new RespEntity(RespCode.ERROR,msgpassword);
        }
    }

    @GetMapping("/user/selectpersonalvideo")    //个人中心查出视频管理的视频
    public RespEntity selectPersonalVideo(HttpServletRequest request,
                                          @RequestParam(value = "vname", defaultValue = "") String vname,
                                          @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,   //在参数里接受当前是第几页 pageNum,以及每页显示多少条数据 pageSize.默认值分别是1和7
                                          @RequestParam(value = "pageSize", defaultValue = "5") int pageSize)
    {
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        PageInfo selectpersonalvideo=userService.selectPersonalVideo(loginUser.getUid(),vname,pageNum,pageSize);
        return new RespEntity(RespCode.SUCCESS,selectpersonalvideo);
    }

    @DeleteMapping("/user/deletepersonalvideo")     //个人中心视频管理 删除视频
    public RespEntity deletePersonalVideo(@RequestParam("vid") Integer vid){
        boolean deletepersonalvideo=userService.deletePersonalVideo(vid);
        if (deletepersonalvideo==true){
            return new RespEntity(RespCode.SUCCESS);
        }else
            return new RespEntity(RespCode.ERROR);
    }

    @GetMapping("/user/selectlikevideo")    //个人中心查出视频管理的视频
    public RespEntity selectLikeVideo(HttpServletRequest request,
                                          @RequestParam(value = "vname", defaultValue = "") String vname,
                                          @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,   //在参数里接受当前是第几页 pageNum,以及每页显示多少条数据 pageSize.默认值分别是1和7
                                          @RequestParam(value = "pageSize", defaultValue = "5") int pageSize)
    {
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        PageInfo selectlikevideo=userService.selectLikeVideo(loginUser.getUid(),vname,pageNum,pageSize);
        return new RespEntity(RespCode.SUCCESS,selectlikevideo);
    }

    @PostMapping("/uploading")   //上传视频
    public RespEntity uploadingVideo(HttpServletRequest request,@RequestParam("file") MultipartFile file,
            @RequestParam(value = "vname") String vname, @RequestParam(value = "vtag") String vtag,
            @RequestParam(value = "introduce") String introduce)
    {
        Map<String,Object> map=new HashMap<>();
        boolean uploadingvideo=userService.uploadingVideo(request,vname,vtag,introduce,map,file);
        String msguploading=(String)map.get("msguploading");
        if (uploadingvideo==true){
            return new RespEntity(RespCode.SUCCESS);
        }else{
            return new RespEntity(RespCode.ERROR,msguploading);
        }
    }
}
