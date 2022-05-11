package com.atguigu.fruit.Controller;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.myssm.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

/**
 * @author chen
 * @create 2022-04-10-18:42
 */
@WebServlet("/del.do")
public class DelServlet extends HttpServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidstr = request.getParameter("fid");
        if(StringUtils.isNotEmpty(fidstr)){
            int fid = Integer.parseInt(fidstr);
            fruitDAO.delFruit(fid);
            response.sendRedirect("index");
        }
    }
}
