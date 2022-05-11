package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-15 14:13
 */
public interface TopicDAO {
    //获取指定用户的所有的日志
    public List<Topic> getTopicList(UserBasic userBasic);

    //添加日志
    public void addTopic(Topic topic);

    //删除日志
    public void delTopic(Topic topic);

    //获取指定的日志的信息
    public Topic getTopic(Integer id);
}
