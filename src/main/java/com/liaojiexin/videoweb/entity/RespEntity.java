package com.liaojiexin.videoweb.entity;


/*接口响应至少需要告诉使用方三项信息：状态码、描述、数据。其中，
数据不是每个接口必须的，如果只是一个简单修改的动作可能就没有必须返回数据了。
下面我们定义一个 RespEntity类来封装我们的响应报文model:
 */
public class RespEntity {
    private int code;       //状态
    private String msg;     //信息
    private Object data;    //数据

    public RespEntity(RespCode respCode) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public RespEntity(RespCode respCode, Object data) {
        this(respCode);
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
