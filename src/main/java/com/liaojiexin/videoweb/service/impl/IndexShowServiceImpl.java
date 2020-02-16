package com.liaojiexin.videoweb.service.impl;

import com.liaojiexin.videoweb.entity.RespEntity;
import com.liaojiexin.videoweb.mapper.VideoMapper;
import com.liaojiexin.videoweb.service.IndexShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexShowServiceImpl implements IndexShowService {     //主页服务层
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List countLikes(){       //最受欢迎的视频
        List countlikes =videoMapper.countlikes();
        return countlikes;
    }

    @Override
    public List newestVideo(){  //最新视频
        List newestvideo=videoMapper.newestvideo();
        return newestvideo;
    }

    @Override
    public List randVideo(){  //随机视频
        List randvideo=videoMapper.randvideo();
        return randvideo;
    }

    @Override
    public List countLooks(){  //热门视频
        List countlooks=videoMapper.countlooks();
        return countlooks;
    }

    @Override
    public Object siftVideo(Integer vid){  //精选视频和上边的视频模块
        Object siftvideo=videoMapper.siftvideo(vid);
        return siftvideo;
    }
}
