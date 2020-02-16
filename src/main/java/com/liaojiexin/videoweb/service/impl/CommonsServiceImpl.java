package com.liaojiexin.videoweb.service.impl;

import com.liaojiexin.videoweb.entity.Video;
import com.liaojiexin.videoweb.mapper.VideoMapper;
import com.liaojiexin.videoweb.service.CommonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonsServiceImpl implements CommonsService {   //公共模块
    @Autowired
    private VideoMapper videoMapper;

    @Override   //公共模块滚动视频
    public Video rollVideo(String vtag){
        Video rollvideo=videoMapper.rollvideo(vtag);
        if(rollvideo!=null){
            return rollvideo;
        }
        else{
            return null;
        }
    }
}
