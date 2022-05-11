package com.atguigu.book.dao;

import com.atguigu.book.pojo.Book;

import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-19 17:19
 */
public interface BookDAO {
    List<Book> getBookList();
}
