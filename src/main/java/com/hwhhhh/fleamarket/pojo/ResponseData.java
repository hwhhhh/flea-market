package com.hwhhhh.fleamarket.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:33
 */
@Data
@AllArgsConstructor
public class ResponseData {
    private Integer code;
    private String message;
    private Object data;

    public ResponseData(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMsg();
        this.data = "";
    }

    public ResponseData(ResponseCode responseCode, Object data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMsg();
        this.data = data;
    }
}
