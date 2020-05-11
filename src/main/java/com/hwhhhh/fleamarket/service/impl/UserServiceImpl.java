package com.hwhhhh.fleamarket.service.impl;

import com.hwhhhh.fleamarket.controller.param.LoginReq;
import com.hwhhhh.fleamarket.controller.param.RegisterReq;
import com.hwhhhh.fleamarket.dao.entity.UserEntity;
import com.hwhhhh.fleamarket.dao.repo.UserRepository;
import com.hwhhhh.fleamarket.domain.model.User;
import com.hwhhhh.fleamarket.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/6 11:39
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(RegisterReq registerReq){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(registerReq, userEntity);
        userEntity = this.userRepository.save(userEntity);
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    @Override
    public User login(LoginReq loginReq) {
        UserEntity userEntity = this.userRepository.findByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword());
        if (userEntity != null) {
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            return user;
        }
        return null;
    }

    @Override
    public boolean checkUser(String email) {
        UserEntity userEntity = this.userRepository.findByEmail(email);
        if (userEntity != null) {
            return true;
        }
        return false;
    }

}
