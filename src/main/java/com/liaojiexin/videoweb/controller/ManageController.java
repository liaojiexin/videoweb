package com.liaojiexin.videoweb.controller;

import com.github.pagehelper.PageInfo;
import com.liaojiexin.videoweb.entity.Manage;
import com.liaojiexin.videoweb.entity.RespCode;
import com.liaojiexin.videoweb.entity.RespEntity;
import com.liaojiexin.videoweb.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController     //视频审核管理模块
public class ManageController {

    @Autowired
    private ManageService manageService;

    @PostMapping("/managelogin/login")  //管理员登录模块
    public RespEntity register(@RequestParam("mname") String mname,
                               @RequestParam("mpassword") String mpassword,
                                HttpSession session)
    {
        boolean is=manageService.isLogin(mname,mpassword,session);
        String msgmanagelogin=(String)session.getAttribute("msgmanagelogin");
        if(is==true){
            return new RespEntity(RespCode.SUCCESS);
        }
        else{
            return new RespEntity(RespCode.ERROR,msgmanagelogin);
        }
    }

    @PostMapping("/manage/selectmanager")        //查出管理员个人所有信息
    public RespEntity selectManager(HttpServletRequest request){
        Manage manager=manageService.selectManager(request);
        return new RespEntity(RespCode.SUCCESS,manager);
    }

    @PutMapping("/manage/updatemanager")        //修改管理员个人资料
    public RespEntity updateManager(HttpServletRequest request,
                                 @RequestParam("mname") String mname,
                                 @RequestParam("email") String email,
                                 @RequestParam("cellphone") String cellphone)
    {
        Manage loginManager = (Manage)request.getSession().getAttribute("loginManager");
        Manage manager=new Manage();
        manager.setMid(loginManager.getMid());
        manager.setCellphone(cellphone);
        manager.setEmail(email);
        manager.setMname(mname);
        Map<String,Object> map=new HashMap<>();
        boolean updatemanager=manageService.updateManager(manager,map);
        if(updatemanager==true)
        {
            return new RespEntity(RespCode.SUCCESS);
        }else{
            String msgupdatemanager=(String)map.get("msgupdatemanager");
            return new RespEntity(RespCode.ERROR,msgupdatemanager);
        }
    }

    @PostMapping("/manage/updatepassword")    //修改密码
    public RespEntity updatePassword(HttpServletRequest request,
                                     @RequestParam("oldPassword") String oldPassword,
                                     @RequestParam("newPassword") String newPassword,
                                     @RequestParam("newagPassword") String newagPassword)
    {
        Map<String,Object> map=new HashMap<>();
        boolean updatepassword=manageService.updatePassword(request,oldPassword,newPassword,newagPassword,map);
        if(updatepassword==true){
            return new RespEntity(RespCode.SUCCESS);
        }else{
            String msgmanagepassword=(String)map.get("msgmanagepassword");
            return new RespEntity(RespCode.ERROR,msgmanagepassword);
        }
    }

    @GetMapping("/manage/selectpersonalvideo")    //查出未审核的视频
    public RespEntity selectPersonalVideo(@RequestParam(value = "vname", defaultValue = "") String vname,
                                          @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,   //在参数里接受当前是第几页 pageNum,以及每页显示多少条数据 pageSize.默认值分别是1和7
                                          @RequestParam(value = "pageSize", defaultValue = "5") int pageSize)
    {
        PageInfo selectpersonalvideo=manageService.selectPersonalVideo(vname,pageNum,pageSize);
        return new RespEntity(RespCode.SUCCESS,selectpersonalvideo);
    }

    @GetMapping("/download/{vid}")   //视频下载
    public void downloadVideo(@PathVariable("vid") Integer vid,HttpServletRequest request, HttpServletResponse response)
    {
        manageService.downloadVideo(vid,request,response);
    }

    @PutMapping("/auditVideo/{z}")       //审核视频时操作      z:操作类型，vid：操作的视频，file1：封面，file2：视频
    public RespEntity auditVideo(@PathVariable("z") Integer z,
                                 @RequestParam("vid") Integer vid,
                                 @RequestParam(value = "file1",required = false) MultipartFile file1,
                                 @RequestParam(value="file2",required = false) MultipartFile file2)
    {
        Map<String,Object> map=new HashMap<>();
        boolean auditVideo=manageService.auditVideo(z,vid,file1,file2,map);
        String msgauditVideo=(String)map.get("msgauditVideo");
        if (auditVideo==true)
            return new RespEntity(RespCode.SUCCESS);
        else
            return new RespEntity(RespCode.ERROR,msgauditVideo);
    }

}
