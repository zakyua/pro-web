package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.HostReply;

/**
 * @author ccstart
 * @create 2022-04-15 21:05
 */
public interface HostReplyService {
    //根据这个reply的id来获取主人回复
    HostReply getHostReplyByReplyId(Integer replyId);

    //根据id删除主人回复
    void delHostReply(Integer id);
}
