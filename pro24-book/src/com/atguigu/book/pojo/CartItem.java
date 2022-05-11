package com.atguigu.book.pojo;

/**
 * @author ccstart
 * @create 2022-04-19 10:30
 */

//应该设计一个购物车的类，代表购物车实体
public class CartItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private User userBean;

    public CartItem() {
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

    public User getUserBean() {
        return userBean;
    }

    public void setUserBean(User userBean) {
        this.userBean = userBean;
    }
}
