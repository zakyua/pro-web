package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-15 15:30
 */
public interface TopicService {
    //查询当前用户的日志
    List<Topic> getTopicList(UserBasic userBasic);

    //根据指定的日志的id查询日志的详情
    Topic getTopicById(Integer id);

    //根据id查询指定的topic信息包含日志关联的作者信息
    public Topic getTopic(Integer id);


    //删除特定的topic方法
    void delTopic(Integer id);

}
