package com.atguigu.book.service;

import com.atguigu.book.pojo.Book;

import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-19 17:22
 */
public interface BookService {
    //查询图书列表
    List<Book> getBookList();
}
