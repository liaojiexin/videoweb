package com.liaojiexin.videoweb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liaojiexin.videoweb.entity.User;
import com.liaojiexin.videoweb.entity.Video;
import com.liaojiexin.videoweb.mapper.CommentsMapper;
import com.liaojiexin.videoweb.mapper.LikesMapper;
import com.liaojiexin.videoweb.mapper.UserMapper;
import com.liaojiexin.videoweb.mapper.VideoMapper;
import com.liaojiexin.videoweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {      //处理用户相关的服务

    @Autowired  //注入DAO
    private UserMapper userMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private LikesMapper likesMapper;

    @Override   //该标签会检查检查重写方法的正确性  //用户登录
    public User userLogin(String username, String password){
        User user=userMapper.userlogin(username,password);
        return user;
    }

    @Override   //判断用户注册合法性，合法则注册
    public boolean isUserRegister(String username,String password,String repassword,String email,Date birthday, Map<String ,Object> map) {
        //正则表达式 验证注册是否符合数字字母下划线规定 https://www.cnblogs.com/haoyul/p/9701085.html
        String regex = "^(\\w){8,16}$";
        Pattern p = Pattern.compile(regex);
        Matcher musername = p.matcher(username);    //用户名
        boolean isMatchusername = musername.matches();

        if (!isMatchusername)       //用户名不规范
        {
            map.put("msgregister","用户名长度或格式不规范，8-16位的数字、字母或下划线");
            return false;
        }
        else if (password.length()<8||password.length()>16)     //密码长度8-16
        {
            map.put("msgregister","密码长度不规范");
            return false;
        }
        else if(email.isEmpty())            //邮箱为空
        {
            map.put("msgregister","邮箱不能为空");
            return false;
        }
        else if (!repassword.equals(password))          //两次密码不同
        {
            map.put("msgregister","两次密码不一致");
            return false;
        }
        else if (username.equals(userMapper.usernameInsert(username)))        //用户名已经存在
        {
            map.put("msgregister","用户名已存在");
            return false;
        }
        else if(email.equals(userMapper.emailInsert(email)))           //邮箱已存在
        {
            map.put("msgregister","邮箱已存在");
            return false;
        }
        else
        {
            User user=new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setBirthday(new Date());
            userMapper.userInsert(user);
            return true;
        }
    }

    @Override   //个人中心 用户所有信息
    public User selectUser(HttpServletRequest request){
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        User user=userMapper.selectUser(loginUser.getUid());
        return user;
    }

    @Override       //修改个人资料
    public boolean updateUser(User user,Map<String,Object> map) {
        //正则表达式 验证注册是否符合数字字母下划线规定
        String regex = "^(\\w){8,16}$";
        Pattern p = Pattern.compile(regex);
        Matcher musername = p.matcher(user.getUsername());    //用户名
        boolean isMatchusername = musername.matches();

        if (!isMatchusername)       //用户名长度8-16
        {
            map.put("msgupdateuser","用户名不规范(长度8-16的数字、字母和下划线)");
            return false;
        }
        else if (userMapper.usernameUpdate(user)>0)        //用户名已经存在
        {
            map.put("msgupdateuser","用户名已存在");
            return false;
        }
        else if(userMapper.emailUpdate(user)>0)           //邮箱已存在
        {
            map.put("msgupdateuser","邮箱已存在");
            return false;
        }
        else
        {
            userMapper.updateUser(user);
            return true;
        }
    }

    @Override       //修改密码
    public boolean updatePassword(HttpServletRequest request,
                                  String oldPassword, String newPassword,
                                  String newagPassword, Map<String, Object> map)
    {
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if(!oldPassword.equals(userMapper.selectPassword(loginUser.getUid())))      //原密码错误
        {
            map.put("msgpassword","原密码错误");
            return false;
        }
        else if(newPassword.length()<8||newPassword.length()>16)     //密码长度8-16
        {
            map.put("msgpassword","新密码长度不规范");
            return false;
        }
        else if (!newPassword.equals(newagPassword))          //两次密码不同
        {
            map.put("msgpassword","设置的两次密码不一致");
            return false;
        }
        else{
            User user=new User();
            user.setUid(loginUser.getUid());
            user.setPassword(newPassword);
            userMapper.updatePassword(user);
            return true;
        }
    }

    @Override   //个人中心查出视频管理的视频
    public PageInfo selectPersonalVideo(Integer uid,String vname,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);//底层实现原理采用改写语句  将下面的方法中的sql语句获取到然后做个拼接 limit
        try{
            //全部的视频信息
            List selectpersonalvideo=videoMapper.selectPersonalVideo(uid,vname);
            // 封装分页之后的数据  返回给客户端展示  PageInfo做了一些封装 作为一个类
            PageInfo pageInfoSelectPersonalVideo = new PageInfo(selectpersonalvideo);
            //所有分页属性都可以从pageInfoDemo拿到
            return pageInfoSelectPersonalVideo;
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
    }

    @Override   //个人中心视频管理 删除视频
    public boolean deletePersonalVideo(Integer vid) {
        //删除磁盘中的视频
        Video v = videoMapper.selectByPrimaryKey(vid);
        String url=v.getUrl();
        File fileurl = new File(System.getProperty("user.dir") + "/src/main/resources/static" + url);
        if (fileurl.exists()) {        //如果文件存在
            fileurl.delete();      //文件删除  https://blog.csdn.net/weixin_43790879/article/details/103155429
        }
        //删除磁盘中的图片
        String image=v.getImageurl();
        File fileimage = new File(System.getProperty("user.dir") + "/src/main/resources/static" + image);
        if (fileimage.exists()) {        //如果文件存在
            fileimage.delete();      //文件删除  https://blog.csdn.net/weixin_43790879/article/details/103155429
        }
        //更新数据
        likesMapper.deletePersonalVideo(vid);
        commentsMapper.deletePersonalVideo(vid);
        videoMapper.deletePersonalVideo(vid);
        return true;
    }

    @Override    //个人中心喜欢的视频查询
    public PageInfo selectLikeVideo(Integer uid, String vname, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);//底层实现原理采用改写语句  将下面的方法中的sql语句获取到然后做个拼接 limit
        try{
            //全部的视频信息
            List selectlikevideo=videoMapper.selectLikeVideo(uid,vname);
            // 封装分页之后的数据  返回给客户端展示  PageInfo做了一些封装 作为一个类
            PageInfo pageInfoSelectLikeVideo = new PageInfo(selectlikevideo);
            //所有分页属性都可以从pageInfoDemo拿到
            return pageInfoSelectLikeVideo;
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
    }

    @Override //上传视频
    public boolean uploadingVideo(HttpServletRequest request, String vname,
                                  String vtag, String introduce, Map<String, Object> map,
                                  MultipartFile file)
    {
        //文件上传
        // 获取文件名，带后缀
        String originalFilename = file.getOriginalFilename();
        // 获取文件的后缀格式
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        //上传视频时查出查出最大的vid号，加一加到视频文件名内方便查找管理
        int vid=videoMapper.maxVid();
        //（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+vname.xxx+"\n,xxx指的是fileSuffix获得的后缀
        String fileName =vid+"_"+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + vname+"."+fileSuffix;
        // 该方法返回的为当前项目的工作目录，即在哪个地方启动的java线程  当前为E:\graduation\videoweb
        String dirPath = System.getProperty("user.dir");
        //文件存储路径
        String path = dirPath+"/src/main/resources/static/video/videourl/" +fileName;
        //创建文件路径
        File dest = new File(path);
        //判断文件是否已经存在
        if (dest.exists())
        {
            map.put("msguploading","上传失败,文件已存在.");
            return false;
        }
        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            //上传文件
            file.transferTo(dest); //文件写入

            //数据存入数据库
            User loginUser = (User)request.getSession().getAttribute("loginUser");
            Video video =new Video();
            video.setUid(loginUser.getUid());
            video.setVtag(vtag);
            video.setVname(vname);
            video.setDate(new Date());
            video.setIntroduce(introduce);
            video.setUrl("/video/videourl/" +fileName);
            videoMapper.inservideo(video);
        } catch (IOException e) {
            map.put("msguploading","上传失败,请先检查上传文件是否按照要求，若不能解决再联系管理员.");
            return false;
        }

        return true;
    }
}
