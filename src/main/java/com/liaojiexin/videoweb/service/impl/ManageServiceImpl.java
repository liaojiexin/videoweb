package com.liaojiexin.videoweb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liaojiexin.videoweb.entity.Manage;
import com.liaojiexin.videoweb.mapper.ManageMapper;
import com.liaojiexin.videoweb.mapper.VideoMapper;
import com.liaojiexin.videoweb.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ManageServiceImpl implements ManageService {       //管理员模块
    @Autowired
    private ManageMapper manageMapper;
    @Autowired
    private VideoMapper videoMapper;

    @Override   //管理员登录
    public boolean isLogin(String mname, String mpassword, HttpSession session) {
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
            session.setAttribute("msgmanagelogin","用户名密码错误,请重新输入！");
            return false;
        }
    }

    @Override   //管理员个人资料查询
    public Manage selectManager(HttpServletRequest request) {
        Manage loginManager = (Manage) request.getSession().getAttribute("loginManager");
        Manage manager=manageMapper.selectManager(loginManager.getMid());
        return manager;
    }

    @Override   //管理员资料修改
    public boolean updateManager(Manage manager, Map<String, Object> map) {
        //Java处理正则验证手机号-详解  https://www.cnblogs.com/haoyul/p/9701085.html
        String regex = "^((13[0-9])|(14[579])|(15([0-3,5-9]))|(16[6])|(17[0135678])|(18[0-9]|19[89]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(manager.getCellphone());
        boolean isMatch = m.matches();

        if (manager.getMname().length()<8||manager.getMname().length()>16)       //用户名长度8-16
        {
            map.put("msgupdatemanager","用户名长度不规范(长度8-16的数字、字母和下划线)");
            return false;
        }
        else if (manageMapper.mnameUpdate(manager)>0)        //用户名已经存在
        {
            map.put("msgupdatemanager","用户名已存在");
            return false;
        }
        else if(manageMapper.cellphoneUpdate(manager)>0)           //号码已存在
        {
            map.put("msgupdatemanager","该手机号码已存在");
            return false;
        }
        else if(!isMatch)           //手机号码格式不对
        {
            map.put("msgupdatemanager","手机号码格式不对");
            return false;
        }
        else if(manageMapper.emailUpdate(manager)>0)           //邮箱已存在
        {
            map.put("msgupdatemanager","邮箱已存在");
            return false;
        }
        else
        {
            manageMapper.updateManager(manager);
            return true;
        }
    }

    @Override   //管理员修改密码
    public boolean updatePassword(HttpServletRequest request, String oldPassword,
                                  String newPassword, String newagPassword, Map<String, Object> map)
    {
        Manage loginManager = (Manage) request.getSession().getAttribute("loginManager");
        if(!oldPassword.equals(manageMapper.selectPassword(loginManager.getMid())))      //原密码错误
        {
            map.put("msgmanagepassword","原密码错误");
            return false;
        }
        else if(newPassword.length()<8||newPassword.length()>16)     //密码长度8-16
        {
            map.put("msgmanagepassword","新密码长度不规范");
            return false;
        }
        else if (!newPassword.equals(newagPassword))          //两次密码不同
        {
            map.put("msgmanagepassword","设置的两次密码不一致");
            return false;
        }
        else{
            Manage manager=new Manage();
            manager.setMid(loginManager.getMid());
            manager.setMpassword(newPassword);
            manageMapper.updatePassword(manager);
            return true;
        }
    }

    @Override   //查出未审核的视频
    public PageInfo selectPersonalVideo(String vname, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);//底层实现原理采用改写语句  将下面的方法中的sql语句获取到然后做个拼接 limit
        try{
            //全部的视频信息
            List selectpersonalvideo=videoMapper.selectPersonalVideo1(vname);
            // 封装分页之后的数据  返回给客户端展示  PageInfo做了一些封装 作为一个类
            PageInfo pageInfoSelectPersonalVideo = new PageInfo(selectpersonalvideo);
            //所有分页属性都可以从pageInfoDemo拿到
            return pageInfoSelectPersonalVideo;
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
    }
}
