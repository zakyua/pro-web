package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.TopicDAO;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-15 14:47
 */
public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    //获取指定用户的所有的日志
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {

        String sql = "select * from t_topic where author = ?";
        return super.executeQuery(sql,userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {

    }

    @Override
    public void delTopic(Topic topic) {

    }

    @Override
    public Topic getTopic(Integer id) {
        return null;
    }
}
