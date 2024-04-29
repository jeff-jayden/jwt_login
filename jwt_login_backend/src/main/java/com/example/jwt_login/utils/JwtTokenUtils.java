package com.example.jwt_login.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwt_login.pojo.User;
import com.example.jwt_login.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenUtils {
    private static UserService staticUserService;

    @Resource
    private UserService userService;

    @PostConstruct
    public void setStaticUserService() {
        staticUserService = userService;
    }

    //生成token
    public static String genToken(String userId, String sign) {
        return JWT.create().withAudience(userId) //作为载荷存userId
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))//2小时token过期
                .sign(Algorithm.HMAC256(sign)); //以password作为token密钥
    }

    /**
     * 获取当前登录的用户信息
     */
    public static User getCurrentUser() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }
            if (StrUtil.isBlank(token)) {
//                log.error("获取当前登录的token失败，token:{}", token);
                return null;
            }
            //解析token，获取用户的id
            String adminId = JWT.decode(token).getAudience().get(0);
            return staticUserService.getUser(Integer.valueOf(adminId));
        } catch (Exception e) {
//            log.error("获取当前登录的管理员信息失败，token={}", token, e);
            return null;
        }
    }
}
