package com.hwhhhh.fleamarket.controller;

import com.hwhhhh.fleamarket.controller.param.LoginReq;
import com.hwhhhh.fleamarket.controller.param.RegisterReq;
import com.hwhhhh.fleamarket.controller.param.UserStatusReq;
import com.hwhhhh.fleamarket.domain.pojo.ResponseCode;
import com.hwhhhh.fleamarket.domain.pojo.ResponseData;
import com.hwhhhh.fleamarket.domain.model.User;
import com.hwhhhh.fleamarket.service.UserService;
import com.hwhhhh.fleamarket.utils.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:43
 */
@RestController
@Slf4j
public class UserController extends BaseController{
    /**
     * @param logInReq login请求，包含邮箱和密码
     * @return 返回请求，记录是否登录成功
     */
    @PostMapping("/user")
    public ResponseData Login(@RequestBody LoginReq logInReq) {
        User user = this.userService.login(logInReq);
        if (user == null) {
            return new ResponseData(ResponseCode.WRONG_LOGIN);//如果用户名或密码错误
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

    /**
     * 上传用户头像
     * @param id
     * @param file
     * @return
     */
    @PostMapping("/users/{id}/images")
    public ResponseData uploadImg(@PathVariable long id, @RequestParam("file")MultipartFile file) {
        try {
            String url = ImageUtil.uploadImg(file, ImageUtil.USER_PROFILE);
            if (this.userService.saveImgURL(id, url)) {
                return new ResponseData(ResponseCode.SUCCSEE, url);
            } else {
                return new ResponseData(ResponseCode.BAD_REQUEST);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseData(ResponseCode.BAD_REQUEST);
        }
    }

    /**
     * 获取正在审核中的用户
     * @return
     */
    @GetMapping("/users")
    public ResponseData getRegUsers() {
        return new ResponseData(ResponseCode.SUCCSEE, this.userService.getUsersByRole(UserService.STATUS_AUDIT));
    }

    @PutMapping("/users/{id}")
    public ResponseData setUserRole(@PathVariable("id") long id, @RequestBody UserStatusReq userStatusReq) {
        if (this.userService.setUserStatus(id, userStatusReq.getRole())){
            return new ResponseData(ResponseCode.SUCCSEE);
        }
        return new ResponseData(ResponseCode.SERVER_WRONG);
    }
}
