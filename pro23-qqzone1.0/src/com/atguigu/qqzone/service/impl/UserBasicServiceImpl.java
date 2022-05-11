package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.UserBasicDAO;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-15 15:00
 */
public class UserBasicServiceImpl implements UserBasicService {

    private UserBasicDAO userBasicDAO =null;
    //登录
    @Override
    public UserBasic login(String loginId, String pwd) {
        UserBasic usBasic = userBasicDAO.getUsBasic(loginId, pwd);
        return usBasic;
    }

    //获取当年用户的所有好友
    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {


        //这里的list中只有userBasic的好友的id
        List<UserBasic> userBasicList = userBasicDAO.getUserBasicList(userBasic);
        List<UserBasic> friendList = new ArrayList<>(userBasicList.size());
        //根据
        for (int i = 0; i < userBasicList.size(); i++) {
            //将userBasicList里的存储的好友取出来，但是这个好友的对象只有id有值
            UserBasic friend = userBasicList.get(i);
            //我们需要根据这个好友的id把这个好友的信息查出来
            friend = userBasicDAO.getUserBasicById(friend.getId());
            friendList.add(friend);
        }
        return friendList;
    }

    //根据id查询用户信息
    @Override
    public UserBasic getUserBasic(Integer id) {

        return  userBasicDAO.getUserBasicById(id);
    }


}
