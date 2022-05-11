package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-15 14:18
 */
public interface ReplyDAO {
    //获取指定日志的回复列表
    public List<Reply> getReplyList(Topic topic);

    //添加回复
    public void addReply(Reply reply);

    //删除回复
    public void delReply(Integer id);



    //根据id获取到回复
    Reply getReplyById(Integer id);



}
