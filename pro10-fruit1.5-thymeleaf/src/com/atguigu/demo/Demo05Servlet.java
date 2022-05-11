package com.atguigu.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chen
 * @create 2022-04-10-14:58
 */
//演示全局作用域application保存作用域
@WebServlet("/demo05")
public class Demo05Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.向application保存作用域中保存数据
        ServletContext application = request.getServletContext();
        application.setAttribute("uname","lina");
        //客户端重定向
//        response.sendRedirect("demo06");

        //服务器内部转发
        request.getRequestDispatcher("demo06").forward(request,response);
    }
}
