package com.atguigu.book.dao;

import com.atguigu.book.pojo.User;

/**
 * @author ccstart
 * @create 2022-04-19 11:06
 */
public interface UserDAO {
    //根据用户名，密码查找用户
    User getUser(String uname,String pwd);

}
