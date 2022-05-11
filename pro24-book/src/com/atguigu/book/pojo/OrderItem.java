package com.atguigu.book.pojo;

/**
 * @author ccstart
 * @create 2022-04-19 10:25
 */
public class OrderItem {
    private Integer id;
    private Book book;//对应数据库里的是book的id
    private Integer buyCount;

    //订单详情所属于的订单
    private OrderBean orderBean;//对应数据库里的是orderBean的id

    public OrderItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public OrderBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }
}
