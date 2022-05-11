package com.atguigu.book.controller;


import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.BookService;
import com.atguigu.book.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-19 11:03
 */
public class UserController {

    private UserService userService;
    private BookService bookService;
    //登录
    public String login(String uname, String pwd, HttpSession session){
        User user = userService.login(uname, pwd);

        if(user != null){
            List<Book> bookList = bookService.getBookList();
            session.setAttribute("bookList",bookList);
            return "index";
        }else {
            return "user/login";
        }



    }
}
