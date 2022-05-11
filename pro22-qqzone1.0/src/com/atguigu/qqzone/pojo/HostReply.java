package com.atguigu.qqzone.pojo;

import java.util.Date;

/**
 * @author chen
 * @create 2022-04-15-10:31
 */
public class HostReply {
private Integer id;
private String content;
private Date hostReplyDate;
private UserBasic author;
private Reply reply;//针对的那一个回复

    public HostReply(){}

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

    public Date getHostReplyDate() {
        return hostReplyDate;
    }

    public void setHostReplyDate(Date hostReplyDate) {
        this.hostReplyDate = hostReplyDate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }
}
