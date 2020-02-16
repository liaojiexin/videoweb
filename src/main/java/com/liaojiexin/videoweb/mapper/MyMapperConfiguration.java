package com.liaojiexin.videoweb.mapper;

import com.liaojiexin.videoweb.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper  //mybatis配置版  resources/mybatis/mapper/MyMapper.xml下配置
public interface MyMapperConfiguration {

    public User getUserById(Integer uid);   //同个id查询

    public void insertUser(User user);      //插入
}
