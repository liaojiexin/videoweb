package com.liaojiexin.videoweb.controller;

import com.liaojiexin.videoweb.entity.RespCode;
import com.liaojiexin.videoweb.entity.RespEntity;
import com.liaojiexin.videoweb.service.IndexShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController     //返回json数据
public class IndexController {      //主页控制层

    @Autowired
    private IndexShowService indexShowService;

    @GetMapping(value = "/indexright")      //主页右边模块：最受欢迎+最新视频
    public RespEntity countLikes(){
        List countlikes=indexShowService.countLikes();      //最受欢迎的视频
        RespEntity respEntity1=new RespEntity(RespCode.SUCCESS,countlikes);
        List newestvideo=indexShowService.newestVideo();        //最新视频
        RespEntity respEntity2=new RespEntity(RespCode.SUCCESS,newestvideo);
        Map map=new HashMap();
        map.put("1",respEntity1);
        map.put("2",respEntity2);
        return new RespEntity(RespCode.SUCCESS,map);
    }

    @GetMapping(value = "/indexleft")   //主页左边模块：热门+随机视频
    public RespEntity randVideo(){
        List countlooks=indexShowService.countLooks();  //热门视频
        RespEntity respEntity1=new RespEntity(RespCode.SUCCESS,countlooks);
        List randvideo=indexShowService.randVideo();    //随机视频
        RespEntity respEntity2=new RespEntity(RespCode.SUCCESS,randvideo);
        Map map=new HashMap();
        map.put("1",respEntity1);
        map.put("2",respEntity2);
        return new RespEntity(RespCode.SUCCESS,map);
    }

    @GetMapping(value = "/siftvideo/{vid}")  //精选视频和上边的视频模块
    public RespEntity siftVideo(@PathVariable("vid") Integer vid){
        Object siftvideo=indexShowService.siftVideo(vid);
        RespEntity respEntity=new RespEntity(RespCode.SUCCESS,siftvideo);
        return respEntity;
    }
}
