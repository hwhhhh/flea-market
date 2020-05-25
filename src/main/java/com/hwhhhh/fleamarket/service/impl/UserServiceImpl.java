package com.hwhhhh.fleamarket.service.impl;

import com.hwhhhh.fleamarket.controller.param.LoginReq;
import com.hwhhhh.fleamarket.controller.param.RegisterReq;
import com.hwhhhh.fleamarket.dao.entity.UserEntity;
import com.hwhhhh.fleamarket.dao.repo.UserRepository;
import com.hwhhhh.fleamarket.domain.UserDTO;
import com.hwhhhh.fleamarket.domain.model.User;
import com.hwhhhh.fleamarket.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        userEntity.setRole(-1);
        userEntity = this.userRepository.save(userEntity);
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    @Override
    public User login(LoginReq loginReq) {
        UserEntity userEntity = this.userRepository.findByEmailAndPassword(loginReq.getEmail(), loginReq.getPassword());
        if (userEntity != null) {
            if (userEntity.getRole() == loginReq.getRole()) {
                User user = new User();
                BeanUtils.copyProperties(userEntity, user);
                return user;
            } else if (userEntity.getRole() == 1) {
                User user = new User();
                BeanUtils.copyProperties(userEntity, user);
                return user;
            }
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

    @Override
    public boolean saveImgURL(long id, String url) {
        UserEntity entity = this.userRepository.getOne(id);
        entity.setPhotoUrl(url);
        this.userRepository.save(entity);
        return true;
    }

    /**
     *
     * @param role 用户的状态，0为普通用户，1为管理员，-1为注册审核中的用户
     * @return
     */
    @Override
    public List<UserDTO> getUsersByRole(int role) {
        List<UserEntity> userEntities = this.userRepository.findAllByRole(role);
        List<UserDTO> users = userEntities.stream().map(entity -> {
            UserDTO user = new UserDTO();
            BeanUtils.copyProperties(entity, user);
            return user;
        }).collect(Collectors.toList());
        return users;
    }

    @Override
    public boolean setUserStatus(long id, int role) {
        UserEntity entity = this.userRepository.getOne(id);
        entity.setRole(role);
        this.userRepository.save(entity);
        return true;
    }
}
