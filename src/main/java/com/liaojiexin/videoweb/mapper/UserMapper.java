package com.liaojiexin.videoweb.mapper;

import com.liaojiexin.videoweb.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    //用户登录  用@Param在mapper.xml中可以省略parameterType
    User userlogin(@Param("username") String username, @Param("password") String password);
    //用户注册
    int userInsert(User user);
    //注册时查询是否有重复的用户名
    String usernameInsert(@Param("username") String username);
    //注册时查询是否有重复的邮箱
    String emailInsert(@Param("email") String email);
    //查询用户所有信息
    User selectUser(@Param("uid") Integer uid);
    //修改个人资料
    int updateUser(User user);
    //修改个人资料时查询是否有重复的用户名
    int usernameUpdate(User user);
    //修改个人资料时查询是否有重复的邮箱
    int emailUpdate(User user);
    //修改密码
    int updatePassword(User user);
    //修改密码时检查原密码是否正确
    String selectPassword(@Param("uid") Integer uid);
}
