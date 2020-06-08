package com.liaojiexin.videoweb.service.impl;

import com.liaojiexin.videoweb.entity.RespEntity;
import com.liaojiexin.videoweb.mapper.VideoMapper;
import com.liaojiexin.videoweb.service.IndexShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

//@CacheConfig(cacheNames="xxx")  //缓存公共配置，例如这里指定了cacheNames，则下面的方法就不需要再指定
@Service
public class IndexShowServiceImpl implements IndexShowService {     //主页服务层
    @Autowired
    private VideoMapper videoMapper;

    /*
     * @Cacheable将方法的运行结果进行缓存，以后要用相同的数据，直接从缓存中获取，不用调用方法；
     *
     * 原理：
     *   1、自动配置类；CacheAutoConfiguration
     *   2、缓存的配置类
     *   org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration【使用redis时】
     *   org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
     *   org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration【默认】
     *   org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
     *   3、哪个配置类默认生效：SimpleCacheConfiguration；
     *
     *   4、给容器中注册了一个CacheManager：ConcurrentMapCacheManager
     *   5、可以获取和创建ConcurrentMapCache类型的缓存组件；他的作用将数据保存在ConcurrentMap中；
     *
     *   运行流程：
     *   @Cacheable：
     *   1、方法运行之前，先去查询Cache（缓存组件），按照cacheNames指定的名字获取；
     *      （CacheManager先获取相应的缓存），第一次获取缓存如果没有Cache组件会自动创建。
     *   2、去Cache中查找缓存的内容，使用一个key，默认就是方法的参数；
     *      key是按照某种策略生成的；默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key；
     *          SimpleKeyGenerator生成key的默认策略；
     *                  如果没有参数；key=new SimpleKey()；
     *                  如果有一个参数：key=参数的值
     *                  如果有多个参数：key=new SimpleKey(params)；
     *   3、没有查到缓存就调用目标方法；
     *   4、将目标方法返回的结果，放进缓存中
     *
     *   @Cacheable标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数的值作为key去查询缓存，
     *   如果没有就运行方法并将结果放入缓存；以后再来调用就可以直接使用缓存中的数据；
     *
     *   核心：
     *      1）、使用CacheManager【ConcurrentMapCacheManager】按照名字得到Cache【ConcurrentMapCache】组件
     *      2）、key使用keyGenerator生成的，默认是SimpleKeyGenerator
     *
     * 几个属性说明：
     *   1.cacheNames/value：指定缓存组件的名字；
     * CacheManager管理多个Cache组件的，对缓存的真正CRUD操作再Cache组件中，每一个缓存组件有自己唯一一个名字
     *   2.key：缓存数据使用的key，可以用它来指定，默认是使用方法参数的值，也可以编写SpEL表达是来表示。
     *   3.keyGenerator:key的生成器；可以自己指定key的生成器的组件id（自己写一个key生成器：参考MyCacheConfig.java文件）
     *       key和keyGenerator二选一使用
     *   4.cacheManager：指定缓存的管理器；或者cacheResolver指定获取缓存解析器
     *   5.condition：指定符合条件的情况下才缓存；
     *   6.unless：否定缓存；当unless指定的条件未true，方法的返回值就不会被缓存；可以获取到结果进行判断。例如unless="#result==null",返回的结果为空时不缓存.
     *   7.sync:缓存是否使用同步模式.
     * */
    @Cacheable(cacheNames ={"countlikes"} /*,keyGenerator = "myKeyGenerator"*/)
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

    @Cacheable(cacheNames = {"countLooks"})
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

/*
 * @CachePut：既调用方法（即一定会到查数据库那一步，所以一般用于修改数据），又更新缓存数据；同步更新缓存
 * 修改了数据库的某个数据，同时更新缓存；
 * 运行时机：
 *  1、先调用目标方法
 *  2、将目标方法的结果缓存起来
 *
 * 测试步骤：
 *  1、查询1号员工；查到的结果会放在缓存中；
 *          key：1  value：lastName：张三
 *  2、以后查询还是之前的结果
 *  3、更新1号员工；【lastName:zhangsan；gender:0】
 *          将方法的返回值也放进缓存了；
 *          key：传入的employee对象  值：返回的employee对象；
 *  4、查询1号员工？
 *      应该是更新后的员工；
 *          key = "#employee.id":使用传入的参数的员工id；
 *          key = "#result.id"：使用返回后的id
 *             @Cacheable的key是不能用#result
 *      为什么是没更新前的？【1号员工没有在缓存中更新，CachePut如果要结合Cacheable使用的话，
 *      他们两个的cacheNames和key/keyGenerator应该保证是同一个，从而保证缓存存在同一个的】
 *
 *
 *
 * @CacheEvict：缓存清除
 * @CacheEvict(value="要删除的cache名称"，key="cache中的key,可省略，默认就为方法参数")
 *
 *
 * @Caching：相当于Cacheable、CachePut、CacheEvict的组合注解，当缓存规则比较复杂的时候就可以用该注解。
 **/
