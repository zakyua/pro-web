package com.atguigu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author chen
 * @create 2022-04-08-18:12
 */

//通过不同的浏览器获取Demo04Servlet保存的数据
public class Demo05Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object uname = request.getSession().getAttribute("uname");
        System.out.println("uname = " + uname);
    }
}
