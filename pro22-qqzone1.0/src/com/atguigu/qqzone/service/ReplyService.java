package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-15 20:43
 */
public interface ReplyService {
    //根据topic的id来查询关联的所有的回复
    List<Reply> getReplyListByTopicId(Integer id);

    //添加一条回复
    void addReply(Reply reply);

    //删除一条回复
    public void delReply(Integer id);


    //删除指定日志关联的所有的回复
    void delReplyList(Topic topic);

}
