package com.liaojiexin.videoweb.entity;

public class Comments extends CommentsKey {     //视频评论
    private String comment;     //评论

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}