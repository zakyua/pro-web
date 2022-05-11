package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-15 15:41
 */
public class UserController {
    private UserBasicService userBasicService;
    private TopicService topicService;


    //验证登录
    public String login(String loginId, String pwd, HttpSession session){
        //1.登陆验证
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if(userBasic != null){
            //2.1获取当前用户的好友列表(这里的好友已经查到了)
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            //2.2获取当前用户的日志列表
            List<Topic> topicList = topicService.getTopicList(userBasic);

            //把查询出来的好友和日志设置到用户中去
            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);

            session.setAttribute("userBasic",userBasic);
            return "index";
        }else{
            return "login";
        }


    }
}
