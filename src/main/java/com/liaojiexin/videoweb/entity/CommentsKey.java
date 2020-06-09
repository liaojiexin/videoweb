package com.liaojiexin.videoweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class CommentsKey implements Serializable {
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  //https://www.cnblogs.com/huanggy/p/9471827.html
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date commenttime;       //评论时间

    private Integer uid;        //用户id

    private Integer vid;        //视频id

    //    多对一，多条评论对应一个评论人
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    //    多对一，多条评论对应一个视频
    private Video video;
    public Video getVideo() {
        return video;
    }
    public void setVideo(Video video) {
        this.video = video;
    }

    public Date getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(Date commenttime) {
        this.commenttime = commenttime;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }
}