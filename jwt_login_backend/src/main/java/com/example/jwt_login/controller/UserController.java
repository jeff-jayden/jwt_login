package com.example.jwt_login.controller;


import com.example.jwt_login.pojo.User;
import com.example.jwt_login.service.UserService;
import com.example.jwt_login.utils.Code;
import com.example.jwt_login.utils.JwtTokenUtils;
import com.example.jwt_login.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    @ResponseBody
    public User getUser(@PathVariable Integer userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<User> getUser() {
        return userService.getAll();
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User user) {
        User loginUser = userService.login(user);
        Integer code = loginUser != null ? Code.GET_OK : Code.GET_ERR;
        String msg = loginUser != null ? "登录成功" : "账户不存在或密码错误";

        String token = JwtTokenUtils.genToken(loginUser.getId().toString(), loginUser.getPassword());
        loginUser.setToken(token);

        return new Result(code,loginUser,msg);
    }

}
