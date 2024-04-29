package com.example.jwt_login.service;

import com.example.jwt_login.pojo.User;

import java.util.List;

public interface UserService {
    User getUser(Integer userId);
    User login(User user);
    List<User> getAll();
}
