package com.atguigu.qqzone.pojo;


import java.util.Date;

/**
 * @author chen
 * @create 2022-04-15-10:31
 */
public class Reply {
private  Integer id;
private String content;
private Date replyDate;
private UserBasic author;//这里针对的是一个作者
private Topic topic;//这里针对的是一篇日志(m:1)
//一个回复里会有主人回复
private HostReply hostReply;//1:1


    public Reply(){}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public HostReply getHostReply() {
        return hostReply;
    }

    public void setHostReply(HostReply hostReply) {
        this.hostReply = hostReply;
    }
}
