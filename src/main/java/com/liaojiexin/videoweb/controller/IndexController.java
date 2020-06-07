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

    /*
    * @Cacheable将方法的运行结果进行缓存，以后要用相同的数据，直接从缓存中获取，不用调用方法；
    *
    * 几个属性说明：
    *   1.cacheNames/value：指定缓存组件的名字；
    * CacheManager管理多个Cache组件的，对缓存的真正CRUD操作再Cache组件中，每一个缓存组件有自己唯一一个名字
    *   2.key：缓存数据使用的key，可以用它来指定，默认是使用方法参数的值，也可以编写SpEL表达是来表示。
    *   3.keyGenerator:key的生成器；可以自己指定key的生成器的组件id
    *       key和keyGenerator二选一使用
    *   4.cacheManager：指定缓存的管理器；或者cacheResolver指定获取缓存解析器
    *   5.condition：指定符合条件的情况下才缓存；
    *   6.unless：否定缓存；当unless指定的条件未true，方法的返回值就不会被缓存；可以获取到结果进行判断。例如unless="#result==null",返回的结果为空时不缓存.
    *   7.sync:缓存是否使用同步模式.
    * */
    @Cacheable(cacheNames ={"indexright"} )
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
