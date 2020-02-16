package com.liaojiexin.videoweb.mapper;

import com.liaojiexin.videoweb.entity.LikesKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LikesMapper {
    int insertSelective(LikesKey record);
//    查询是否已经投票
    LikesKey selectlike(LikesKey record);
//    投票
    int insertlike(LikesKey record);
//    取消投票
    int deletelike(LikesKey key);
//个人中心视频管理 删除视频的投票记录
int deletePersonalVideo(@Param("vid") Integer vid);
}