package com.hwhhhh.fleamarket.controller;

import com.hwhhhh.fleamarket.controller.param.LoginReq;
import com.hwhhhh.fleamarket.controller.param.RegisterReq;
import com.hwhhhh.fleamarket.pojo.ResponseCode;
import com.hwhhhh.fleamarket.pojo.ResponseData;
import com.hwhhhh.fleamarket.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:43
 */
@RestController
@Slf4j
public class UserController extends BaseController{
    /**
     * 用户登录，采用get方法
     * @param logInReq login请求，包含邮箱和密码
     * @return 返回请求，记录是否登录成功
     */
    @GetMapping("/users")
    public ResponseData Login(@RequestBody LoginReq logInReq) {
        User user = this.userService.login(logInReq);
        if (user == null) {
            return new ResponseData(ResponseCode.WRONG_LOGIN);//如果用户名或密码错误返回空数据
        }
        return new ResponseData(ResponseCode.SUCCSEE, user);
    }

    /**
     * 用户注册
     * @param registerReq
     * @return
     */
    @PostMapping("/users")
    public ResponseData Register(@RequestBody RegisterReq registerReq) {
        boolean isExist = this.userService.checkUser(registerReq.getEmail());
        if (!isExist) { //如果不存在，则进行注册
            User user = this.userService.registerUser(registerReq);
            return new ResponseData(ResponseCode.SUCCSEE, user);
        }
        return new ResponseData(ResponseCode.WRONG_REGISTER);  //邮箱已注册过
    }
}
