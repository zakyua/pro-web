package com.atguigu.servlets;
import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chen
 * @create 2022-04-06-21:37
 */
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get方式目前不需要设置编码(基于tomcat8)
        /*
        //如果是get请求发送的中文数据，转码优点麻烦(tomcat8之前)
        String fname = request.getParameter("fname");
        //1.将这个字符串打散成字节数组
        byte[] bytes=fname.getBytes(""ISO-8859-1);
        //将字节数组按照设定的编码重新组装成字符串
        fname = new String(btyes,"uts-8");
         */

        //post方式下需要设置字符集,防止中二年乱码
        //需要注意的是，这只编码这一句代码需要加到所有的获取参数动作之前
        request.setCharacterEncoding("utf-8");
        String fname = request.getParameter("fname");
        String pricestr = request.getParameter("price");
        Integer price = Integer.parseInt(pricestr);
        String fcountstr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountstr);
        String remark = request.getParameter("remark");

         
         FruitDAO fruitDAO = new FruitDAOImpl();
        boolean isFlag = fruitDAO.addFruit(new Fruit(0, fname, price, fcount, remark));
        System.out.println(isFlag ? "添加成功！":"添加失败！");

    }
}
