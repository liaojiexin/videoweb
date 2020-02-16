package com.liaojiexin.videoweb.controller;

import com.liaojiexin.videoweb.entity.User;
import com.liaojiexin.videoweb.mapper.MyMapperAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MybatisAnnotationController {

    @Autowired
    MyMapperAnnotation myMapper;

    @GetMapping("/user/{id}")       //访问localhost:8080/user/1查询id为1的信息
    public User getUser(@PathVariable("id") Integer id){  //@PathVariable,路径id传给参数id
        return myMapper.getUser(id);
    }
}
