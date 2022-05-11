package servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author chen
 * @create 2022-04-12-19:15
 */

/*
@WebServlet("/demo01")
 */

public class Demo01Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        //获取XML文件里的配置信息
        ServletConfig config = this.getServletConfig();
        String initParameter = config.getInitParameter("name");

        //获取Servlet上下文(使用上下问配置信息来使用thymeleaf技术)就是application
        ServletContext servletContext = getServletContext();
        String username = servletContext.getInitParameter("username");

        System.out.println("username = " + username);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = request.getServletContext();
        String username = servletContext.getInitParameter("username");
        System.out.println("username = " + username);

        ServletContext servletContext1 = request.getSession().getServletContext();
        String username1 = servletContext1.getInitParameter("username");
        System.out.println("username1 = " + username1);

    }
}
