package com.liaojiexin.videoweb.controller;

import com.github.pagehelper.PageInfo;
import com.liaojiexin.videoweb.entity.RespCode;
import com.liaojiexin.videoweb.entity.RespEntity;
import com.liaojiexin.videoweb.service.SingleShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SingleController {         //播放页面控制层
    @Autowired
    private SingleShowService singleShowService;

    @GetMapping(value = "/single/{vid}")    //直播页查找视频信息,并且观看数+1
    public String singleShow(@PathVariable("vid") Integer vid, Model model){
        Object singleshow=singleShowService.singleVideo(vid);
        model.addAttribute("singleshow",singleshow);
        return "single";
    }

    @ResponseBody
    @GetMapping(value = "/selectcomments/{vid}")    //查询相关视频所有的评论
    public RespEntity selectComments(@PathVariable("vid") Integer vid,
                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,   //在参数里接受当前是第几页 pageNum,以及每页显示多少条数据 pageSize.默认值分别是1和7
                                     @RequestParam(value = "pageSize", defaultValue = "7") int pageSize)
    {
        PageInfo selectcomments=singleShowService.selectComments(vid,pageNum,pageSize);
        RespEntity respEntity=new RespEntity(RespCode.SUCCESS,selectcomments);
        return respEntity;
    }

    @ResponseBody
    @PostMapping("/insertcomments")       //插入评论功能
    public RespEntity insertComments(@RequestParam("vid") Integer vid,@RequestParam("comment") String comment,HttpServletRequest request){
        boolean is=singleShowService.insertComments(vid,comment,request);
        if(is==true){
            return new RespEntity(RespCode.SUCCESS);
        }else
        {
            return new RespEntity(RespCode.ERROR);
        }
    }

    @ResponseBody
    @GetMapping("/islike")      //判断一下是否已经投票
    public RespEntity isLike(@RequestParam("vid") Integer vid,HttpServletRequest request){
        boolean islike=singleShowService.islike(vid,request);
        if(islike==true){       //已经有投票了
            return new RespEntity(RespCode.SUCCESS);
        }else{      //没有投票或者还没登录
            return new RespEntity(RespCode.ERROR);
        }
    }

    @ResponseBody
    @PostMapping("/addlike")    //投票
    public RespEntity addLike(@RequestParam("vid") Integer vid,HttpServletRequest request){
        boolean addlike=singleShowService.addlike(vid,request);
        if(addlike==true){      //投票成功
            return new RespEntity(RespCode.SUCCESS);
        }else{//没有登录投票失败
            return new RespEntity(RespCode.ERROR);
        }
    }

    @ResponseBody
    @DeleteMapping("/deletelike")    //取消投票
    public RespEntity deleteLike(@RequestParam("vid") Integer vid,HttpServletRequest request){
        boolean deleteLike=singleShowService.deleteLike(vid,request);
        if(deleteLike==true){      //取消投票成功
            return new RespEntity(RespCode.SUCCESS);
        }else{//没有登录或取消失败
            return new RespEntity(RespCode.ERROR);
        }
    }
}
