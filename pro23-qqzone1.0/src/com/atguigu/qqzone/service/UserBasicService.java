package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-15 14:56
 */
public interface UserBasicService {
    //登录
    UserBasic login(String loginId,String pwd);

    //获取当前用户的所有好友
    List<UserBasic> getFriendList(UserBasic userBasic);

    //根据id查询用户信息
    UserBasic getUserBasic(Integer id);


}
