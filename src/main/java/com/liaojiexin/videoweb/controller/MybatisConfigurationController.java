package com.liaojiexin.videoweb.controller;

import com.liaojiexin.videoweb.entity.User;
import com.liaojiexin.videoweb.mapper.MyMapperConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MybatisConfigurationController {
    @Autowired
    MyMapperConfiguration myMapperConfiguration;

    @GetMapping("/emp/{id}")
    public User getUser(@PathVariable("id") Integer id){
        return myMapperConfiguration.getUserById(id);
    }
}
