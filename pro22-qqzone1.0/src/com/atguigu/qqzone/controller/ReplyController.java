package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author ccstart
 * @create 2022-04-16 13:24
 */
public class ReplyController {
    private ReplyService replyService;

    //添加回复
    public String addReply(String content,Integer topicId, HttpSession session){
        UserBasic userBasic = (UserBasic)session.getAttribute("userBasic");

        Reply reply = new Reply(content,new Date(),userBasic,new Topic(topicId));
        replyService.addReply(reply);

        //detail/html(重定向)
        return "redirect:topic.do?operate=topicDetail&id="+topicId;


    }


    //删除回复
    public String delReply(Integer replyId,Integer topicId){
        replyService.delReply(replyId);
        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }
}
