package com.liaojiexin.videoweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Video implements Serializable {
    private Integer vid;        //视频id
    private String vname;       //视频名
    private String vtag;        //视频标签
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  //https://www.cnblogs.com/huanggy/p/9471827.html
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;          //上传日期
    private Integer uid;        //上传人id
    private String introduce;       //视频介绍
    private String url;             //视频路径
    private String imageurl;        //视频封面路径
    private Byte state;         //视频状态，通过1/审核0/不通过:涉及非法内容-1/不通过：文件格式不规范-2
    private String special;     //专题
    private Integer countcomments;      //评论数
    private Integer countlikes;         //喜欢数
    private Integer countlooks;         //观看数

    //    多对一，多个视频由一个用户上传
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    //    一对多，一个视频有多个用户喜欢的记录
    List<LikesKey> likesKeys;
    public List<LikesKey> getLikesKeys() {
        return likesKeys;
    }
    public void setLikesKeys(List<LikesKey> likesKeys) {
        this.likesKeys = likesKeys;
    }

    //    一对多，一个视频多条评论
    List<Comments> comments;
    public List<Comments> getComments() {
        return comments;
    }
    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname == null ? null : vname.trim();
    }

    public String getVtag() {
        return vtag;
    }

    public void setVtag(String vtag) {
        this.vtag = vtag == null ? null : vtag.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl == null ? null : imageurl.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special == null ? null : special.trim();
    }

    public Integer getCountcomments() {
        return countcomments;
    }

    public void setCountcomments(Integer countcomments) {
        this.countcomments = countcomments;
    }

    public Integer getCountlikes() {
        return countlikes;
    }

    public void setCountlikes(Integer countlikes) {
        this.countlikes = countlikes;
    }

    public Integer getCountlooks() {
        return countlooks;
    }

    public void setCountlooks(Integer countlooks) {
        this.countlooks = countlooks;
    }
}