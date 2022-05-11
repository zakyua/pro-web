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


}
