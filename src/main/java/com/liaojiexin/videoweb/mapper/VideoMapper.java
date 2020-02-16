package com.liaojiexin.videoweb.mapper;

import com.liaojiexin.videoweb.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VideoMapper {
    int deleteByPrimaryKey(Integer vid);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Integer vid);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);
    //首页查询专题,视频详细信息和用户名,评论数，喜欢数，观看数
    List specialSelect(@Param("special") String special);
    //首页查询分类,视频详细信息和用户名,评论数，喜欢数，观看数
    List labelSelect(@Param("vtag") String vtag);
    //首页搜索功能,视频详细信息和用户名,评论数，喜欢数，观看数
    List checkSelect(@Param("vname") String vname);
    //进入播放页时观看数+1
    int updatelooks(@Param("vid") Integer vid);
    //播放页视频的详细信息
    Object singlevideo(@Param("vid") Integer vid);
    //最受欢迎的视频
    List countlikes();
    //最新视频
    List newestvideo();
    //随机视频
    List randvideo();
    //热门视频
    List countlooks();
    //精选视频和上边的视频模块
    Object siftvideo(@Param("vid") Integer vid);
    //公共模块滚动视频
    Video rollvideo(@Param("vtag") String vtag);
    //插入评论时，视频表的评论数加1
    int updatecomments(@Param("vid") Integer vid);
    //投票时，视频表的喜欢数加1
    int addlikes(@Param("vid") Integer vid);
    //取消投票时，视频表的喜欢数减1
    int subtractlikes(@Param("vid") Integer vid);
    //个人中心视频管理 查出视频
    List selectPersonalVideo(@Param("uid") Integer uid,@Param("vname") String vname);
    //个人中心视频管理 删除视频
    int deletePersonalVideo(@Param("vid") Integer vid);
    //个人中心喜欢的视频查询
    List selectLikeVideo(@Param("uid") Integer uid,@Param("vname") String vname);
    //上传视频
    int inservideo(Video video);
}