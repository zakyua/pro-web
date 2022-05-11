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
@WebServlet("/demo06")
public class Demo06Sevlet extends HttpServlet {
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取application保存作用域的数据
        ServletContext application = request.getServletContext();
        Object uname = application.getAttribute("uname");
        System.out.println("uname = " + uname);


    }
    
}
