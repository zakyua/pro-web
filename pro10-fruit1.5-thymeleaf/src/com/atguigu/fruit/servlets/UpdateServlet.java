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
import java.util.function.DoublePredicate;

/**
 * @author chen
 * @create 2022-04-10-17:59
 */
@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");

        //去更新数据库的数据
        //获取请求中的数据
        String fidstr = request.getParameter("fid");
        int fid = Integer.parseInt(fidstr);
        String fname = request.getParameter("fname");
        String pricestr = request.getParameter("price");
        int price = Integer.parseInt(pricestr);
        String fcountstr = request.getParameter("fcount");
        int fcount = Integer.parseInt(fcountstr);
        String remark = request.getParameter("remark");


        fruitDAO.updateFruit(new Fruit(fid,fname,price,fcount,remark));

        //重新去请求一个更新过数据的页面
        //请求index组件，到indexServlet将数据库里的数据在重新加载到页面上
        response.sendRedirect("index");

    }
}


