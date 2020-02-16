package com.liaojiexin.videoweb.component;

import com.liaojiexin.videoweb.entity.RespCode;
import com.liaojiexin.videoweb.entity.RespEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice       //全局异常类
public class UploadingHandlerInterceptor {
    /* spring默认上传大小1MB 超出大小捕获异常MaxUploadSizeExceededException */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public RespEntity handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        Map<String,Object> map=new HashMap<>();
        map.put("msguploading","文件大小超过2GB，上传失败");
        String msguploading=(String)map.get("msguploading");
        return new RespEntity(RespCode.ERROR,msguploading);
    }
}
