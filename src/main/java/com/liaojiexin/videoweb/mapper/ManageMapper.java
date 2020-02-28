package com.liaojiexin.videoweb.mapper;

import com.liaojiexin.videoweb.entity.Manage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ManageMapper {
    int deleteByPrimaryKey(Integer mid);
    int insert(Manage record);
    int insertSelective(Manage record);
    Manage selectByPrimaryKey(Integer mid);
    int updateByPrimaryKeySelective(Manage record);
    int updateByPrimaryKey(Manage record);

    //管理员登录
    Manage manageLogin(Manage manage);
    //审核模块查询管理员个人资料
    Manage selectManager(Integer mid);
    //修改管理员资料时查看是否有重复的用户名
    int mnameUpdate(Manage manager);
    //修改管理员资料时查看是否有重复的电话号码
    int cellphoneUpdate(Manage manager);
    //修改管理员资料时查看是否有重复的邮箱
    int emailUpdate(Manage manager);
    //修改管理员资料
    void updateManager(Manage manager);
    //修改密码时检测原密码是否正确
    String selectPassword(@Param("mid") Integer mid);
    //修改密码
    void updatePassword(Manage manager);
}