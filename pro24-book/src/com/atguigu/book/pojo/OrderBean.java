package com.atguigu.book.pojo;

import java.util.Date;
import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-19 10:14
 */
public class OrderBean {
    private Integer id;
    private String orderNo;//订单编号
    private Date orderDate;
    private Double orderMoney;
    private User user;
    private String orderStatus;

    //订单的详情
    private List<OrderItem> orderDetailList;

    public OrderBean(){}

    public OrderBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItem> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderItem> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
