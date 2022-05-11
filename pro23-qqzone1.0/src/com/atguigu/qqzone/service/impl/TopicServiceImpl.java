package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.TopicDAO;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-15 15:32
 */
public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO =null;
    private ReplyService replyService;
    private UserBasicService userBasicService;

    //查询当前用户的日志
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {

        //
        return topicDAO.getTopicList(userBasic);
    }


    //根据id查询指定的topic信息包含日志关联的作者信息
    @Override
    public Topic getTopic(Integer id){
        //现在的这个日志只有日志内容，还没有回复的信息和主人的回复(其中这个日志的作者属性只有id)
        Topic topic = topicDAO.getTopic(id);
        //给当前topic日志的作者属性设置值
        UserBasic author = topic.getAuthor();
        author = userBasicService.getUserBasic(author.getId());
        topic.setAuthor(author);
        return  topic;
    }

    //删除日志
    @Override
    public void delTopic(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        if(topic !=null){
           //1.先将日志的所有回复都删除
            replyService.delReplyList(topic);
            //2.删除日志
            topicDAO.delTopic(topic);
        }
    }


    //根据指定的日志的id查询日志的详情
    @Override
    public Topic getTopicById(Integer id) {
        //这个topic是包含作者信息的
        Topic topic = getTopic(id);
        //这里就把日志的所有的回复都查到了
        List<Reply> replyList= replyService.getReplyListByTopicId(topic.getId());

        topic.setReplyList(replyList);
        return topic;
    }
}
