package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.ReplyDAO;
import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.HostReplyService;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-15 20:48
 */
public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyDAO;
    //此处引入的其他的pojo类的Service接口而不是dao接口
    //其他pojo对应的业务逻辑是封装在service层的，我们需要去调用别人的业务逻辑方法，而不要深入考虑人家内部的细节
    private HostReplyService hostReplyService;
    private UserBasicService userBasicService;


    //根据topic的id来查询关联的所有的回复
    @Override
    public List<Reply> getReplyListByTopicId(Integer id) {

        //1.获取日志的回复的列表
        List<Reply> replyList = replyDAO.getReplyList(new Topic(id));

        for (int i = 0; i < replyList.size(); i++) {
            Reply reply = replyList.get(i);

            //2.1获取这个日志的回复的列表的主人回复
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);

            //2.2获取这个日志的回复的作者的信息(将关联的作者设置进去)
            UserBasic author = userBasicService.getUserBasic(reply.getAuthor().getId());
            reply.setAuthor(author);

        }


        return replyList;
    }
    //添加一条回复
    @Override
    public void addReply(Reply reply) {
       replyDAO.addReply(reply);
    }

    @Override
    public void delReply(Integer id) {
        //根据id获取到reply
        Reply reply = replyDAO.getReplyById(id);
        if(reply != null){
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            //如果这条回复有关联的主人回复，应该先删除主人回复删除
            if(hostReply !=null){
                hostReplyService.delHostReply(hostReply.getId());
            }
            //删除reply
            replyDAO.delReply(id);

        }



    }

    //删除当前日志的所有关联信息
    @Override
    public void delReplyList(Topic topic) {
        //先获取当前日志的所有回复
        List<Reply> replyList = replyDAO.getReplyList(topic);
        if(replyList !=null){
            for (Reply reply :
                    replyList) {
                delReply(reply.getId());
            }

        }
    }
}
