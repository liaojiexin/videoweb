package com.liaojiexin.videoweb;

import com.liaojiexin.videoweb.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

//Spring Boot单元测试
@RunWith(SpringRunner.class)
@SpringBootTest
public class VideowebApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("test");
    }

    /*
    * 测试redis是否连接成功
    * String(字符串)、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
    * stringRedisTemplate.opsForXXX()来获取对应的数据，例如：stringRedisTemplate.opsForValue()来
    * 操作String类型的数据
    * */
    @Autowired
    StringRedisTemplate stringRedisTemplate;  //操作k-v都是字符串

    @Autowired
    RedisTemplate redisTemplate;    //k-v都是对象

    @Autowired
    RedisTemplate<Object,User> userRedisTemplate; //json序列化，MyRedisConfig.java文件

    //字符串测试
    @Test
    public void redisStringTest(){
        stringRedisTemplate.opsForValue().set("a","hello");     //添加key为a，value为hello到redis中
        String a=stringRedisTemplate.opsForValue().get("a");     //获取key为a的value
        System.out.println(a);
    }

    //对象测试
    @Test
    public void redisObjectTest() throws ParseException {
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
        //一般将数据以json的方式保存
        //（方法1）自己将对象转为json
        //（方法2）redisTemplate默认的序列化规则，默认为jdk，我们可以自己改成json序列化器
        User user=new User();
        user.setUid(1);
        user.setPassword("abcd");
        user.setUsername("sss");
        user.setEmail("aaa@qq.com");
        user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("2020-10-10"));

//        redisTemplate.opsForValue().set("b",user); //这里默认为jdk序列化方法
        userRedisTemplate.opsForValue().set("b",user);   //这里用json序列化方法
    }

}
