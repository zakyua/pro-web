package com.atguigu.demo;

import com.mysql.fabric.Server;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpClient;

/**
 * @author chen
 * @create 2022-04-10-14:28
 */
//demo01、demo02演示获取request作用域的数据
@WebServlet("/demo01")
public class Demo01Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取request作用域中保存的数据
        request.setAttribute("uname","lina");

        //服务器重定向(这个时候客户端就向服务器又重新发了请求，所一demo02就获取不到作用域的值)
//        response.sendRedirect("demo02");

        //服务器内部转发(对于客户端来说还是一次请求)
        request.getRequestDispatcher("demo02").forward(request,response);
    }
}
