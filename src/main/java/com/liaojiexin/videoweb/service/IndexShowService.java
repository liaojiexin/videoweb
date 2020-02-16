package com.liaojiexin.videoweb.service;

import java.util.List;

public interface IndexShowService {
    List countLikes();      //最受欢迎的视频
    List newestVideo();     //最新视频
    List randVideo();       //随机视频
    List countLooks();       //热门视频
    Object siftVideo(Integer vid);      //精选视频和上边的视频模块
}
