package com.liaojiexin.videoweb.entity;

import java.io.Serializable;

public class Comments extends CommentsKey implements Serializable {     //视频评论
    private String comment;     //评论

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}