package com.atguigu.book.dao.Impl;


import com.atguigu.book.Base.BaseDAO;
import com.atguigu.book.dao.BookDAO;
import com.atguigu.book.pojo.Book;
import com.atguigu.myssm.basedao.ConnUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-19 14:35
 */
public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {

   //获取所有的图书
    @Override
    public List<Book> getBookList() {

        Connection conn = ConnUtil.getConn();
        String sql = "select * from t_book";
        try {
            return super.getForList(conn, sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
//        return executeQuery("select * from t_book where bookStatus=0");
    }
}
