package com.liaojiexin.videoweb.service;

import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

public interface SingleShowService  {       //播放页服务层
    Object singleVideo(Integer vid);  //播放页视频的详细信息
    PageInfo selectComments(Integer vid, Integer pageNum, Integer pageSize);    //查询相关视频所有的评论
    boolean insertComments(Integer vid, String comment,HttpServletRequest request);        //插入评论
    boolean islike(Integer vid,HttpServletRequest request);        //判断是否已经投票
    boolean addlike(Integer vid,HttpServletRequest request);        //喜欢视频投票
    boolean deleteLike(Integer vid,HttpServletRequest request);        //取消视频投票
}
