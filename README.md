# videoweb 视频网站
## 2020/2/28
个人做的视频网站，现在为第一版，功能包括：用户登录、注册；用户可以播放、浏览、搜索、投票、评论、上传视频；前台的实现基本完成。
本来不打算做后台系统（即管理员），但是后来发现还是需要，不然很多操作实现很麻烦。所以近期会把管理员功能补充上，后期会再改成分布式系统。

涉及的技术：后端主要是Spring Boot+Mybatis；前端主要是HTML+CSS+JavaScript+jQuery+Ajax+Bootstrap；前端模板引擎为thymeleaf（用的比较少，主要还是
后端返回json数据，前端ajax进行数据展示处理）；另外还用到centos操作系统，dockert进行虚拟机环境的搭建，nginx进行反向代理和负载均衡。
database文件为MySQL导出的文件。

## 2020/3/4
后台管理员模块补充上，并且简单的部署到阿里云
