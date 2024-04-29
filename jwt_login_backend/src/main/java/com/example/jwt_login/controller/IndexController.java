package com.example.jwt_login.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class IndexController {

    @RequestMapping("/index")
    @ResponseBody
    public static String index() {
        return "success";
    }

}
