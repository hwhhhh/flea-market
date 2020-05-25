package com.hwhhhh.fleamarket.service;

import com.hwhhhh.fleamarket.controller.param.LoginReq;
import com.hwhhhh.fleamarket.controller.param.RegisterReq;
import com.hwhhhh.fleamarket.domain.UserDTO;
import com.hwhhhh.fleamarket.domain.model.User;

import java.util.List;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:39
 */
public interface UserService {
    int STATUS_COMMON = 0, STATUS_ADMIN = 1, STATUS_AUDIT = -1; // 用户的状态，0为普通用户，1为管理员，-1为注册审核中的用户

    User registerUser(RegisterReq registerReq);//注册一个用户
    User login(LoginReq loginReq);//用户登录
    boolean checkUser(String email);//检查用户是否存在
    boolean saveImgURL(long id, String url);
    List<UserDTO> getUsersByRole(int role);// 获取申请某种状态的的用户
    boolean setUserStatus(long id, int role); // 设置用户的状态信息
}
