package com.liaojiexin.videoweb.entity;

public enum RespCode {

    SUCCESS(0, "请求成功"),
    ERROR(-1, "请求失败");

    private int code;
    private String msg;

    RespCode(int code, String msg) {
        this.code=code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
