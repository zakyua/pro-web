package com.atguigu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author chen
 * @create 2022-04-08-17:37
 */

//演示Session
public class Demo03Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session，如果获取不到，则创建一个新的
        HttpSession session = req.getSession();
        System.out.println("session ID = " + session.getId());

    }
}
