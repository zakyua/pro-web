package com.atguigu.book.service;


import com.atguigu.book.pojo.User;

/**
 * @author ccstart
 * @create 2022-04-19 11:14
 */
public interface UserService {
    //登录
    User login(String uname, String pwd);
}
