package com.example.jwt_login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.jwt_login.dao.UserDao;
import com.example.jwt_login.pojo.User;
import com.example.jwt_login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(Integer userId) {
        return userDao.getUser(userId);
    }

    @Override
    public User login(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, user.getUsername());
        return userDao.selectOne(lqw);
    }

    @Override
    public List<User> getAll() {
        return userDao.selectList(null);
    }


}
