package com.liaojiexin.videoweb.mapper;

import com.liaojiexin.videoweb.entity.Manage;
import org.apache.ibatis.annotations.Mapper;
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
}