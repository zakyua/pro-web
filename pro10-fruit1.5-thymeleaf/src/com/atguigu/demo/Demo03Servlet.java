package com.atguigu.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.interfaces.RSAKey;

/**
 * @author chen
 * @create 2022-04-10-14:48
 */
//demo03、demo04演示session保存作用域的数据
@WebServlet("/demo03")
public class Demo03Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.向session作用域中保存数据
        request.getSession().setAttribute("uname","lina");
        //2.客户端重定向
//        response.sendRedirect("demo04");
        //服务器内部转发
        request.getRequestDispatcher("demo04").forward(request,response);
    }
}
