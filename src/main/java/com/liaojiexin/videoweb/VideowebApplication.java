package com.liaojiexin.videoweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan(value = "com.liaojiexin.videoweb.mapper") //该标签可以让指定包下的文件为Mapper，这样就该包下文件可以省略@Mapper标签
@SpringBootApplication    //@SpringBootApplication来标注一个主程序类，说明这是一个spring boot应用
public class VideowebApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideowebApplication.class, args);     //spring应用启动起来
    }

}
