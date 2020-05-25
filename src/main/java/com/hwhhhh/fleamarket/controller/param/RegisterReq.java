package com.hwhhhh.fleamarket.controller.param;

import lombok.Data;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 15:40
 */
@Data
public class RegisterReq {
    private String name;
    private String email;
    private String password;
}
