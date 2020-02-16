package com.liaojiexin.videoweb.mapper;

import com.liaojiexin.videoweb.entity.Comments;
import com.liaojiexin.videoweb.entity.CommentsKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentsMapper {
    int deleteByPrimaryKey(CommentsKey key);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(CommentsKey key);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKey(Comments record);
//    查询视频全部评论
    List selectcomments(@Param("vid") Integer vid);
//    插入评论
    int insertcomments(Comments record);
//个人中心视频管理 删除视频的评论记录
int deletePersonalVideo(@Param("vid") Integer vid);
}
