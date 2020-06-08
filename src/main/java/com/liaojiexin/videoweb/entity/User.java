package com.liaojiexin.videoweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {         //用户
    private Integer uid;                //用户id
    private String username;            //用户名
    private String password;        //用户密码
    private String email;           //用户邮箱
    @DateTimeFormat(pattern="yyyy-MM-dd")  //https://www.cnblogs.com/huanggy/p/9471827.html
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;     //用户出生日期

    //    一对多，一个用户有多个用户喜欢的记录
    List<LikesKey> likesKeys;
    public List<LikesKey> getLikesKeys() {
        return likesKeys;
    }
    public void setLikesKeys(List<LikesKey> likesKeys) {
        this.likesKeys = likesKeys;
    }

    //    一对多，一个用户多条评论
    List<Comments> comments;
    public List<Comments> getComments() {
        return comments;
    }
    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    //  一对多，一个用户有多个视频
    List<Video> video;
    public List<Video> getVideo() {
        return video;
    }
    public void setVideo(List<Video> video) {
        this.video = video;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}