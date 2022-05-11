package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.HostReplyDAO;
import com.atguigu.qqzone.pojo.HostReply;

/**
 * @author ccstart
 * @create 2022-04-15 21:11
 */
public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {

    //根据指定的reply的id来查询主人回复
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        String sql ="SELECT * FROM t_host_reply WHERE reply = ?";
        return super.load(sql,replyId);
    }

    //根据id删除主人回复
    @Override
    public void delHostReply(Integer id) {
        String sql ="delete from t_host_reply where id=? ";
        super.executeUpdate(sql,id);
    }
}
