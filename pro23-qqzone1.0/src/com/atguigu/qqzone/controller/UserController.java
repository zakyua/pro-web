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
    private UserBasicService userBasicService =null;
    private TopicService topicService =null;


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

            //userBasic保存的登陆者的信息
            //friend保存的是当前进入是谁的空间
            session.setAttribute("userBasic",userBasic);
            session.setAttribute("friend",userBasic);
            return "index";
        }else{
            return "login";
        }


    }



    //进去好友的空间
    public String friend(Integer id,HttpSession session){
        //根据这个好友的id找到这个好友的信息，获取他的信息然后去覆盖主页面
        UserBasic currFriend = userBasicService.getUserBasic(id);
        //获取这个用户的所有的日志
        List<Topic> topicList = topicService.getTopicList(currFriend);
        //将日志信息给这个用户设置上去
        currFriend.setTopicList(topicList);


        //这个时候就把friend给覆盖了，展现的就是要查询的那个好友的页面
        session.setAttribute("friend",currFriend);

        //跳转页面
        return "index";
    }
}
