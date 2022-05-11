package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.HostReplyDAO;
import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.service.HostReplyService;

/**
 * @author ccstart
 * @create 2022-04-15 21:07
 */
public class HostReplyServiceImpl implements HostReplyService {

    private HostReplyDAO hostReplyDAO;
    //根据这个reply的id来获取主人回复
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {

        return  hostReplyDAO.getHostReplyByReplyId(replyId);
    }

    //根据id删除主人回复
    @Override
    public void delHostReply(Integer id) {
        hostReplyDAO.delHostReply(id);
    }
}
