package com.liaojiexin.videoweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
import java.util.Arrays;

@Configuration
public class MyCacheCongfig {   //自己定义一个key生成器

    @Bean("myKeyGenerator") //把方法注入到容器中并命名未myKeyGenerator,在要使用改生成器的Cacheable中使用
    public KeyGenerator keyGenerator(){
        return new KeyGenerator(){

            @Override
            public Object generate(Object target, Method method, Object... params) {
                return method.getName()+"["+ Arrays.asList(params).toString()+"]";  //自己定义生成key的策略
            }
        };
    }
}
