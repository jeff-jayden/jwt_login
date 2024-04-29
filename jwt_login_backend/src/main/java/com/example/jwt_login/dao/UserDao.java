package com.example.jwt_login.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jwt_login.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("select * from user where id=#{userId}")
    User getUser(@Param("userId") Integer userId);

    @Select("select * from user where name=#{user.name}")
    User login(@Param("name") User user);
}
