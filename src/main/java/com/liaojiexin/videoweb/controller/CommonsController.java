package com.liaojiexin.videoweb.controller;

import com.github.pagehelper.PageInfo;
import com.liaojiexin.videoweb.entity.RespCode;
import com.liaojiexin.videoweb.entity.RespEntity;
import com.liaojiexin.videoweb.entity.Video;
import com.liaojiexin.videoweb.service.ArchiveShowService;
import com.liaojiexin.videoweb.service.CommonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommonsController {        //公共模块的控制层
    @Autowired
    private ArchiveShowService archiveShowService;
    @Autowired
    private CommonsService commonsService;

    @GetMapping(value = "/checkSelect")             //搜索控制层
    public String checkShow(@RequestParam("vname") String vname,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,   //在参数里接受当前是第几页 pageNum,以及每页显示多少条数据 pageSize.默认值分别是1和10
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                             Model model)
    {
        model.addAttribute("vname",vname);  //把vname传给分页栏

        PageInfo checkSelect=archiveShowService.checkSelect(vname,pageNum,pageSize);
        model.addAttribute("pages",checkSelect);

//        处理前端分页栏数字变动问题
        List pagenums = new ArrayList();
        if(pageNum>3&&checkSelect.getPages()>5)  //当前端页数超过第三页时,并且查询到的总页数大于5
        {
            if(pageNum>=checkSelect.getPages()-2){ //checkSelect.getPages()总页数，如果前端页数大于等于总页数-2时
                pagenums.add(checkSelect.getPages()-4);
                pagenums.add(checkSelect.getPages()-3);
                pagenums.add(checkSelect.getPages()-2);
                pagenums.add(checkSelect.getPages()-1);
                pagenums.add(checkSelect.getPages());
            }
            else{
                pagenums.add(pageNum-2);
                pagenums.add(pageNum-1);
                pagenums.add(pageNum);
                pagenums.add(pageNum+1);
                pagenums.add(pageNum+2);
            }
        }else{          //前端页数没超过第三页时
            if(checkSelect.getPages()<5) //如果总页数小于5
            {
                for(int i=0;i<checkSelect.getPages();i++)
                    pagenums.add(i+1);
            }else{
                pagenums.add("1");
                pagenums.add("2");
                pagenums.add("3");
                pagenums.add("4");
                pagenums.add("5");
            }
        }

        model.addAttribute("pagenums",pagenums);

        //跳到到personal.html页面,thymeleaf默认会拼串，classpath:/templates/xxx.html
        return "archive";       //这里不可以写redirect:/checkarchive，前端页面获取不到model，https://www.wandouip.com/t5i350864/
    }

    @GetMapping(value = "/specialSelect/{special}")     //专题控制层
    public String specialShow(@PathVariable("special") String special,
                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,   //在参数里接受当前是第几页 pageNum,以及每页显示多少条数据 pageSize.默认值分别是1和10
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               Model model)
    {
        PageInfo specialSelect=archiveShowService.specialSelect(special,pageNum,pageSize);
        model.addAttribute("pages",specialSelect);

//        处理前端分页栏数字变动问题
        List pagenums = new ArrayList();
        if(pageNum>3&&specialSelect.getPages()>5)  //当前端页数超过第三页时,,并且查询到的总页数大于5
        {
            if(pageNum>=specialSelect.getPages()-2){ //specialSelect.getPages()总页数，如果前端页数大于等于总页数-2时
                pagenums.add(specialSelect.getPages()-4);
                pagenums.add(specialSelect.getPages()-3);
                pagenums.add(specialSelect.getPages()-2);
                pagenums.add(specialSelect.getPages()-1);
                pagenums.add(specialSelect.getPages());
            }
            else{
                pagenums.add(pageNum-2);
                pagenums.add(pageNum-1);
                pagenums.add(pageNum);
                pagenums.add(pageNum+1);
                pagenums.add(pageNum+2);
            }
        }else{          //前端页数没超过第三页时
            if(specialSelect.getPages()<5) //如果总页数小于5
            {
                for(int i=0;i<specialSelect.getPages();i++)
                    pagenums.add(i+1);
            }else{
                pagenums.add("1");
                pagenums.add("2");
                pagenums.add("3");
                pagenums.add("4");
                pagenums.add("5");
            }
        }

        model.addAttribute("pagenums",pagenums);

        //跳到到personal.html页面,thymeleaf默认会拼串，classpath:/templates/xxx.html
        return "archive";       //这里不可以写redirect:/archive，前端页面获取不到model，https://www.wandouip.com/t5i350864/
    }

    @GetMapping(value = "/labalSelect/{vtag}")      //分类控制层
    public String labelShow(@PathVariable("vtag") String vtag,  //@PathVariable,请求路径的vtag映射到参数vtag
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,   //在参数里接受当前是第几页 pageNum,以及每页显示多少条数据 pageSize.默认值分别是1和10
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                             Model model)
    {
        PageInfo labelSelect= archiveShowService.labelSelect(vtag,pageNum,pageSize);
        model.addAttribute("pages",labelSelect);

        //        处理前端分页栏数字变动问题
        List pagenums = new ArrayList();
        if(pageNum>3&&labelSelect.getPages()>5)  //当前端页数超过第三页时,并且查询到的总页数大于5
        {
            if(pageNum>=labelSelect.getPages()-2){ //labelSelect.getPages()总页数，如果前端页数大于等于总页数-2时
                pagenums.add(labelSelect.getPages()-4);
                pagenums.add(labelSelect.getPages()-3);
                pagenums.add(labelSelect.getPages()-2);
                pagenums.add(labelSelect.getPages()-1);
                pagenums.add(labelSelect.getPages());
            }
            else{
                pagenums.add(pageNum-2);
                pagenums.add(pageNum-1);
                pagenums.add(pageNum);
                pagenums.add(pageNum+1);
                pagenums.add(pageNum+2);
            }
        }else{          //前端页数没超过第三页时
            if(labelSelect.getPages()<5) //如果总页数小于5
            {
                for(int i=0;i<labelSelect.getPages();i++)
                    pagenums.add(i+1);
            }else{
                pagenums.add("1");
                pagenums.add("2");
                pagenums.add("3");
                pagenums.add("4");
                pagenums.add("5");
            }
        }
        model.addAttribute("pagenums",pagenums);

        return "archive";
    }

    @ResponseBody   //返回json值
    @GetMapping(value = "/rollvideo/{vtag}")      //滚动视频控制层
    public RespEntity rollVideo(@PathVariable("vtag") String vtag)
    {
        Video rollvideo=commonsService.rollVideo(vtag);
        RespEntity respEntity=new RespEntity(RespCode.SUCCESS,rollvideo);
        return respEntity;
    }
}
