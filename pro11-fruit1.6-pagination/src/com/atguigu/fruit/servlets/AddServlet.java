package com.atguigu.fruit.Controller;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.DatagramPacket;

/**
 * @author chen
 * @create 2022-04-10-19:10
 */

@WebServlet("/add.to")
public class AddServlet extends HttpServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String fname = request.getParameter("fname");
        String pricestr = request.getParameter("price");
        int price = Integer.parseInt(pricestr);
        String fcountstr = request.getParameter("fcount");
        int fcount = Integer.parseInt(fcountstr);
        String remark = request.getParameter("remark");


        //将这个数据封装到一个Fruit类中
        fruitDAO.addFruit(new Fruit(0,fname,price,fcount,remark));

        response.sendRedirect("index");




    }
}
