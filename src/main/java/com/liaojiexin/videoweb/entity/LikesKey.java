package com.liaojiexin.videoweb.entity;

import java.io.Serializable;

public class LikesKey implements Serializable {     //记录喜欢视频的记录
    private Integer uid;   //用户id

    private Integer vid;    //视频id

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