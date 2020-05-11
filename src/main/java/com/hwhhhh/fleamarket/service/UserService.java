package com.hwhhhh.fleamarket.service;

import com.hwhhhh.fleamarket.controller.param.LoginReq;
import com.hwhhhh.fleamarket.controller.param.RegisterReq;
import com.hwhhhh.fleamarket.domain.model.User;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:39
 */
public interface UserService {
    User registerUser(RegisterReq registerReq);//注册一个用户
    User login(LoginReq loginReq);//用户登录
    boolean checkUser(String email);//检查用户是否存在
}
