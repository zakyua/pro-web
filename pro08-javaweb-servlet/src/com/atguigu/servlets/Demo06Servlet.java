package com.atguigu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Provider;

/**
 * @author chen
 * @create 2022-04-08-20:58
 */

//演示服务器内部转发以及客户端重定向
public class Demo06Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo06....");
        //服务器内部转发(访问的页面还是demo06,但是内容还是demo07的)
        request.getRequestDispatcher("demo07").forward(request,response);

        //服务器重定向(访问的页面直接转到了demo07)
//        response.sendRedirect("demo07");
    }
}
