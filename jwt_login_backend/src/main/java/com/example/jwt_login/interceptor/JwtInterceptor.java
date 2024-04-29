package com.example.jwt_login.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.jwt_login.pojo.User;
import com.example.jwt_login.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取token
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("token");
        }
        //2.开始认证
        if (StrUtil.isBlank(token)) {
            throw new Exception("无token, 请重新登录");
        }

        //获取token中的userId
        String userId;
        User user;
        try {
            //通过token 解码获得userId
            userId = JWT.decode(token).getAudience().get(0);
            System.out.println("userId: " + userId);
            user = userService.getUser(Integer.parseInt(userId));
            System.out.println("user1111" + user);
        } catch (Exception e) {
            String errMsg = "token验证失败，请重新登录";
            log.error(errMsg + ", token" + token, e);
            throw new Exception(errMsg);
        }

        if (user == null) {
            throw new Exception("用户不存在");
        }

        try {
            //用户密码加签验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            System.out.println("jwtVerifier: " + jwtVerifier);
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new Exception("token验证无效，请重新登录");
        }
        return true;
    }

}
