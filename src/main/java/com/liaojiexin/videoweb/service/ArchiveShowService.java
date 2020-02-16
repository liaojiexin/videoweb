package com.liaojiexin.videoweb.service;

import com.github.pagehelper.PageInfo;

public interface ArchiveShowService {     //视频浏览页的服务
    PageInfo specialSelect(String special,int pageNum, int pageSize);     //首页专题页显示
    PageInfo labelSelect(String vtag,int pageNum, int pageSize);        //首页分类显示
    PageInfo checkSelect(String vname,int pageNum, int pageSize);        //首页搜索显示
}
