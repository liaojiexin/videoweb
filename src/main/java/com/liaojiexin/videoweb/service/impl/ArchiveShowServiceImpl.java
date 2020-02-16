package com.liaojiexin.videoweb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liaojiexin.videoweb.mapper.VideoMapper;
import com.liaojiexin.videoweb.service.ArchiveShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveShowServiceImpl implements ArchiveShowService {     //处理视频浏览页的服务

    @Autowired      //注入DAO
    private VideoMapper videoMapper;

    @Override      //首页专题页显示 //该标签会检查检查重写方法的正确性
    public PageInfo specialSelect(String special,int pageNum, int pageSize){      //开启分页插件,放在查询语句上面 帮助生成分页语句
        PageHelper.startPage(pageNum, pageSize);//底层实现原理采用改写语句  将下面的方法中的sql语句获取到然后做个拼接 limit
        try {
        List specialselect=videoMapper.specialSelect(special);      //全部的视频信息
        // 封装分页之后的数据  返回给客户端展示  PageInfo做了一些封装 作为一个类
        PageInfo pageInfoSpecialSelect = new PageInfo(specialselect);
        //所有分页属性都可以从pageInfoDemo拿到
        return pageInfoSpecialSelect;
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
    }

    @Override   //首页分类
    public PageInfo labelSelect(String vtag, int pageNum, int pageSize) {
        // 开启分页插件,放在查询语句上面 帮助生成分页语句
        PageHelper.startPage(pageNum, pageSize);//底层实现原理采用改写语句  将下面的方法中的sql语句获取到然后做个拼接 limit
        try{
        List labelSelect=videoMapper.labelSelect(vtag);      //全部的视频信息
        // 封装分页之后的数据  返回给客户端展示  PageInfo做了一些封装 作为一个类
        PageInfo pageInfoLabelSelect = new PageInfo(labelSelect);
        //所有分页属性都可以从pageInfoDemo拿到
        return pageInfoLabelSelect;
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
    }

    @Override   //首页搜索功能
    public PageInfo checkSelect(String vname, int pageNum, int pageSize) {
        // 开启分页插件,放在查询语句上面 帮助生成分页语句
        PageHelper.startPage(pageNum, pageSize);//底层实现原理采用改写语句  将下面的方法中的sql语句获取到然后做个拼接 limit
        try{
            List checkSelect=videoMapper.checkSelect(vname);      //全部的视频信息
            // 封装分页之后的数据  返回给客户端展示  PageInfo做了一些封装 作为一个类
            PageInfo pageInfoCheckSelect = new PageInfo(checkSelect);
            //所有分页属性都可以从pageInfoDemo拿到
            return pageInfoCheckSelect;
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
    }
}
