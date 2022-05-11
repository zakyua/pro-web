package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-15 20:27
 */
public class TopicController {
    private TopicService topicService;

    //查询日志的详情信息
    public String topicDetail(Integer id, HttpSession session){
        //获取到了当前日志的信息(这里的日志信息就包含了所有的回复)
        Topic topic = topicService.getTopicById(id);

        session.setAttribute("topic",topic);
        return "frames/detail";
    }


    //删除日志
    public String delTopic(Integer topicId){

        topicService.delTopic(topicId);

        return "redirect:topic.do?operate=getTopicList";
    }


    public String getTopicList(HttpSession session){
        //从session中获取当前用户的信息
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        //再次查询当前用户关联的所有的日志
        List<Topic> topicList = topicService.getTopicList(userBasic);
        //设置一下关联的日志列表(因为之前session中关联的friend的topicList于此刻数据库中的不一致)
        userBasic.setTopicList(topicList);
        //重新覆盖friend的信息(为什么不覆盖userBasic中？因为main页面迭代的是friend这个key中的数据)
        session.setAttribute("friend",userBasic);
        return "frames/main";

    }
}
