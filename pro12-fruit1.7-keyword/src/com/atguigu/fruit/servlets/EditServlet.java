package com.atguigu.fruit.Controller;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;
import com.atguigu.myssm.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Queue;

/**
 * @author chen
 * @create 2022-04-10-16:19
 */
@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {

    FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    public  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidstr = request.getParameter("fid");
        if(StringUtils.isNotEmpty(fidstr)){
            int fid = Integer.parseInt(fidstr);
            Fruit fruit = fruitDAO.getFruitByid(fid);
            //这还是一次请求，我们就使用请求保存域保存数据
            request.setAttribute("fruit",fruit);

            //然后跳转一个页面给客户端(使用服务器内部转发)
            super.processTemplate("edit",request,response);


        }
    }
}
