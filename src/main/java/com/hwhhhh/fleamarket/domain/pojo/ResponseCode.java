package com.hwhhhh.fleamarket.domain.pojo;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 17:52
 */
public enum  ResponseCode {
    SUCCSEE(200, "请求成功！"),WRONG_LOGIN(201, "用户名或密码错误"), WRONG_REGISTER(202, "用户名已存在！"),
    BAD_REQUEST(401, "错误的请求，请检查参数及其数值是否有问题！"), SERVER_WRONG(500, "服务器出现异常！");

    private int code;
    private String msg;

    ResponseCode(int code, String msg){
        this.code = code;
        this.msg = msg;
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
    }}
