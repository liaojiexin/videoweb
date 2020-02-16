package com.liaojiexin.videoweb.mapper;


import com.liaojiexin.videoweb.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

//mybatis注解版
@Repository
@Mapper //指定这是一个操作数据库的Mapper
public interface MyMapperAnnotation {

    @Select("select * from user where uid=#{uid}")          //查询
    public User getUser(Integer uid);

    @Delete("delete from user where id=#{uid}")        //删除
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "uid")        //插入   useGeneratedKeys自动生成主键打开，keyProperty设置主键，这样就可以实现主键自增
    @Insert("insert into user values(#{username},#{password},#{email},#{birthday})")
    public int insertUser(User user);

    @Update("update user set username=#{username} where uid=#{uid}")       //修改
    public int updateUser(User user);
}
