package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.HostReply;

/**
 * @author ccstart
 * @create 2022-04-15 21:09
 */
public interface HostReplyDAO {
    //根据指定的reply的id来查询主人回复
    HostReply getHostReplyByReplyId(Integer replyId);

    //根据id删除主人回复
    void delHostReply(Integer id);
}
