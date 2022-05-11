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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//Servlet从3.0版本开始支持注解方式的注册
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response)throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession() ;
        Integer pageNo = 1 ;

        String keyword = null;
        //获取隐藏域里的值
        String oper = request.getParameter("oper");
       //如果这个值是null 并且为search说明这个请求是从表单来的
        if(StringUtils.isNotEmpty(oper) && "search".equals(oper)){
           //去查相应的数据
            pageNo = 1 ;
            keyword = request.getParameter("keyword");
            if(StringUtils.isEmpty(keyword)){
                keyword ="";
            }
            session.setAttribute("keyword",keyword);
        }else {
            //说明此处不是点击表单查询发送过来的请求（比如点击下面的上一页下一页或者直接在地址栏输入网址）
            //此时keyword应该从session作用域获取
            String pagestr = request.getParameter("pageNo");
            if(StringUtils.isNotEmpty(pagestr)){
                pageNo = Integer.parseInt(pagestr);
            }
            Object keywordobj = session.getAttribute("keyword");
            if(keywordobj != null){
                keyword = (String)keywordobj;
            }else {
                keyword = "";
        }
        }


        String pageNoStr = request.getParameter("pageNo");
        if(StringUtils.isNotEmpty(pageNoStr)){
            pageNo = Integer.parseInt(pageNoStr);
        }


        session.setAttribute("pageNo",pageNo);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword,pageNo);

        session.setAttribute("fruitList",fruitList);

        //总记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        //总页数
        int pageCount = (fruitCount+5-1)/5 ;
        /*
        总记录条数       总页数
        1               1
        5               1
        6               2
        10              2
        11              3
        fruitCount      (fruitCount+5-1)/5
         */
        session.setAttribute("pageCount",pageCount);

        //此处的视图名称是 index
        //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
        //逻辑视图名称 ：   index
        //物理视图名称 ：   view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是：      /       index       .html
        super.processTemplate("index",request,response);
    }
}