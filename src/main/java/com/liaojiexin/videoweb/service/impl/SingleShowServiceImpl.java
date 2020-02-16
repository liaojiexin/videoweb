package com.liaojiexin.videoweb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liaojiexin.videoweb.entity.Comments;
import com.liaojiexin.videoweb.entity.LikesKey;
import com.liaojiexin.videoweb.entity.User;
import com.liaojiexin.videoweb.mapper.CommentsMapper;
import com.liaojiexin.videoweb.mapper.LikesMapper;
import com.liaojiexin.videoweb.mapper.VideoMapper;
import com.liaojiexin.videoweb.service.SingleShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class SingleShowServiceImpl implements SingleShowService {       //播放页服务层

    @Autowired      //注入DAO
    private VideoMapper videoMapper;
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private LikesMapper likesMapper;

    @Override   //视频详细信息
    public Object singleVideo(Integer vid) {
        videoMapper.updatelooks(vid);   //观看数+1
        Object singlevideo=videoMapper.singlevideo(vid);
        return singlevideo;
    }

    @Override   //查询相关视频所有的评论
    public PageInfo selectComments(Integer vid,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);//底层实现原理采用改写语句  将下面的方法中的sql语句获取到然后做个拼接 limit
        try{
            List selectcomments=commentsMapper.selectcomments(vid);      //全部的视频信息
            // 封装分页之后的数据  返回给客户端展示  PageInfo做了一些封装 作为一个类
            PageInfo pageInfoSelectComments = new PageInfo(selectcomments);
            //所有分页属性都可以从pageInfoDemo拿到
            return pageInfoSelectComments;
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
    }

    @Override   //插入评论
    public boolean insertComments(Integer vid, String comment,HttpServletRequest request){
        //判断一下当前是否有登录
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if (loginUser==null){   //没有登录
            return false;
        }
        else{
            Comments comments=new Comments();
            comments.setCommenttime(new Date());    //评论时间
            comments.setVid(vid);       //评论视频
            comments.setComment(comment);   //评论内容
            comments.setUid(loginUser.getUid());   //评论人
            commentsMapper.insertcomments(comments);    //插入评论
            videoMapper.updatecomments(vid);    //评论数+1
            return true;
        }
    }

    @Override   //判断是否有投票记录
    public boolean islike(Integer vid,HttpServletRequest request){
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if (loginUser==null){   //没有登录
            return false;
        }
        else{
            LikesKey likesKey=new LikesKey();
            likesKey.setUid(loginUser.getUid());
            likesKey.setVid(vid);
            LikesKey islike =likesMapper.selectlike(likesKey);
            if (islike==null){      //还没投票
                return false;
            }
            else{   //已经投票
                return true;
            }
        }
    }

    @Override   //喜欢视频投票
    public boolean addlike(Integer vid,HttpServletRequest request){
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if(loginUser==null){    //没有登录
            return false;
        }else{
            LikesKey likesKey=new LikesKey();
            likesKey.setVid(vid);
            likesKey.setUid(loginUser.getUid());
            likesMapper.insertlike(likesKey);   //投票
            videoMapper.addlikes(vid);//投票时，视频表的喜欢数加1
            return true;
        }
    }

    @Override   //取消视频投票
    public boolean deleteLike(Integer vid,HttpServletRequest request){
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if(loginUser==null){    //没有登录
            return false;
        }else{
            LikesKey likesKey=new LikesKey();
            likesKey.setVid(vid);
            likesKey.setUid(loginUser.getUid());
            likesMapper.deletelike(likesKey);   //取消投票
            videoMapper.subtractlikes(vid);//投票时，视频表的喜欢数减1
            return true;
        }
    }
}
