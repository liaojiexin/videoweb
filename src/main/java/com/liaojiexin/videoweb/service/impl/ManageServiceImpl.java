package com.liaojiexin.videoweb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liaojiexin.videoweb.entity.Manage;
import com.liaojiexin.videoweb.entity.Video;
import com.liaojiexin.videoweb.mapper.ManageMapper;
import com.liaojiexin.videoweb.mapper.VideoMapper;
import com.liaojiexin.videoweb.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Override   //视频下载  https://www.jianshu.com/p/e678d7b362e1
    public void downloadVideo(Integer vid,HttpServletRequest request, HttpServletResponse response) {
        String url =videoMapper.downloadVideo(vid);
        // 获取文件名  https://blog.csdn.net/he172073675/article/details/78349727/
        String fName = url.trim();
        String fileName = URLEncoder.encode(fName.substring(fName.lastIndexOf("/")+1)); //中文变下划线问题https://www.imooc.com/qadetail/253900
        if (fileName != null) {
            //设置文件路径  System.getProperty("user.dir")该方法返回的为当前项目的工作目录，即在哪个地方启动的java线程  当前为E:\graduation\videoweb
            File file = new File(System.getProperty("user.dir")+"/src/main/resources/static"+url);
            if (file.exists()) {    //判断文件是不是存在
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    //return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
     //   return "下载失败";
    }

    @Override   //审核视频时操作  file1封面  file2视频
    public boolean auditVideo(Integer z, Integer vid,
                              MultipartFile file1, MultipartFile file2,
                              Map<String, Object> map)
    {
        if(z==-1||z==-2)       //不通过：视频涉及非法内容-1  不通过：文件格式不规范-2
        {
            String url = videoMapper.downloadVideo(vid);
            File file = new File(System.getProperty("user.dir") + "/src/main/resources/static" + url);
            if (file.exists()) {        //如果文件存在
                file.delete();      //文件删除  https://blog.csdn.net/weixin_43790879/article/details/103155429
                videoMapper.auditVideo(z,vid,null,null);
                return true;
            }
            else
                map.put("msgauditVideo","异常，文件不存在！");
                return false;
        }
        if(z==1||z==2)        //通过上传封面1  通过上传封面和视频2
        {
            //通过上传封面
            // 获取文件名，带后缀
            String originalFilename = file1.getOriginalFilename();
            //加个视频vid和时间戳，方便查找也尽量避免文件名称重复
            String fileName = vid + "_"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+ originalFilename;
            // 该方法返回的为当前项目的工作目录，即在哪个地方启动的java线程  当前为E:\graduation\videoweb
            String dirPath = System.getProperty("user.dir");
            //文件存储路径
            String path = dirPath+"/src/main/resources/static/video/imagesurl/" +fileName;
            //创建文件路径
            File dest = new File(path);
            //判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
            }
            try {
                //上传文件
                file1.transferTo(dest); //文件写入
                String url = videoMapper.downloadVideo(vid);
                //数据修改
                videoMapper.auditVideo(z,vid,"/video/imagesurl/" +fileName,url);
                if(z==2)     //通过上传新格式的视频
                {
                    //把旧格式视频删除
                    String urlold = videoMapper.downloadVideo(vid);
                    File fileold = new File(System.getProperty("user.dir") + "/src/main/resources/static" + urlold);
                    if (fileold.exists()) {        //如果文件存在
                        fileold.delete();      //文件删除  https://blog.csdn.net/weixin_43790879/article/details/103155429

                        //上传新格式的视频
                        //查出旧视频名称
                        Video v=videoMapper.selectByPrimaryKey(vid);
                        String vname=v.getVname();
                        // 获取文件名，带后缀
                        String originalFilename1 = file2.getOriginalFilename();
                        // 获取文件的后缀格式
                        String fileSuffix = originalFilename1.substring(originalFilename1.lastIndexOf(".") + 1).toLowerCase();
                        //（加个vid和时间戳，方便查找也尽量避免文件名称重复）保存的文件名为: "+vname.xxx+"\n,xxx指的是fileSuffix获得的后缀
                        String fileName1 = vid + "_" +new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + vname+"."+fileSuffix;
                        // 该方法返回的为当前项目的工作目录，即在哪个地方启动的java线程  当前为E:\graduation\videoweb
                        String dirPath1 = System.getProperty("user.dir");
                        //文件存储路径
                        String path1 = dirPath1+"/src/main/resources/static/video/videourl/" +fileName1;
                        //创建文件路径
                        File dest1 = new File(path1);
                        //判断文件是否已经存在
                        if (dest1.exists())
                        {
                            map.put("msgauditVideo","上传失败,视频文件已存在.");
                            return false;
                        }
                        //判断文件父目录是否存在
                        if (!dest1.getParentFile().exists()) {
                            dest1.getParentFile().mkdir();
                        }
                        try {
                            //上传文件
                            file2.transferTo(dest1); //文件写入

                            //修改数据
                            videoMapper.auditVideo(z,vid,"/video/imagesurl/" +fileName,"/video/videourl/" +fileName1);
                        } catch (IOException e) {
                            map.put("msgauditVideo","上传失败,请联系系统维护员！");
                            return false;
                        }
                    }
                }
                return true;
            } catch (IOException e) {
                map.put("msgauditVideo","上传失败,请联系系统人员.");
                return false;
            }
        }
        map.put("msgauditVideo","异常，请联系系统维护员！");
        return false;
    }
}
