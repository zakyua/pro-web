package com.atguigu.book.pojo;

/**
 * @author ccstart
 * @create 2022-04-19 10:04
 */
public class Book {
    private int id;
    private String bookName;
    private  String author;
    private  Double price;
    private Integer salCount; //销售的数量
    private Integer bookCount; //库存
    private String bookImg;
    private Integer bookStatus =0;//状态

    public Book(){}

    public Book(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public Integer getSalCount() {
        return salCount;
    }

    public void setSalCount(Integer salCount) {
        this.salCount = salCount;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }
}
