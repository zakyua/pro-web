package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.ReplyDAO;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-15 20:54
 */
public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {

    //获取指定日志的回复列表
    @Override
    public List<Reply> getReplyList(Topic topic) {
        String sql="SELECT * FROM t_reply WHERE topic = ?";
        return super.executeQuery(sql,topic.getId());
    }

    //添加回复
    @Override
    public void addReply(Reply reply) {
        String sql = "insert into t_reply values(0,?,?,?,?)";
        executeUpdate(sql,reply.getContent(),reply.getReplyDate(),reply.getAuthor().getId(),reply.getTopic().getId());

    }

    //删除回复
    @Override
    public void delReply(Integer id) {
        String sql ="delete from t_reply where id =?";
        executeUpdate(sql,id);
    }

    @Override
    public Reply getReplyById(Integer id) {
        String sql ="select * from t_reply where id =?";
        return load(sql,id);
    }
}
