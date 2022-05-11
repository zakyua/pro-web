package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-15 14:09
 */
public interface UserBasicDAO {
    //根据账号和密码获取特定的用户信息
    public UserBasic getUsBasic(String loginId,String pwd);

    //获取指定用户的所有好友
    public List<UserBasic> getUserBasicList(UserBasic userBasic);


    //根据指定的id查询用户的信息
    public UserBasic getUserBasicById(Integer id);

}
