package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.TopicDAO;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;

import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-15 15:32
 */
public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO =null;

    //查询当前用户的日志
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {

        //
        return topicDAO.getTopicList(userBasic);
    }
}
