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
import java.beans.beancontext.BeanContext;
import java.io.IOException;
import java.util.List;

/**
 * @author chen
 * @create 2022-04-11-10:38
 */
@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码集
        request.setCharacterEncoding("utf-8");
        //获取请求的是哪一个组件
        String operate = request.getParameter("operate");
        //默认显示index组件跳转到index.html
        if(StringUtils.isEmpty(operate)){
            operate = "index";
        }
        switch (operate){
            case "index":
                index(request,response);
                break;
            case "add":
                add(request,response);
                break;
            case "del":
                del(request,response);
                break;
            case "update":
                update(request,response);
                break;
            case "edit":
                edit(request,response);
                break;
             default:
                    new RuntimeException("operate的值不对");

        }


    }

    //更新数据库的数据的组件
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        response.sendRedirect("fruit.do");

    }

    //查看库存信息
    private   void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    //删除的组件
    private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidstr = request.getParameter("fid");
        if(StringUtils.isNotEmpty(fidstr)){
            int fid = Integer.parseInt(fidstr);
            fruitDAO.delFruit(fid);
            response.sendRedirect("fruit.do");
        }
    }

    //添加的组件
    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String fname = request.getParameter("fname");
        String pricestr = request.getParameter("price");
        int price = Integer.parseInt(pricestr);
        String fcountstr = request.getParameter("fcount");
        int fcount = Integer.parseInt(fcountstr);
        String remark = request.getParameter("remark");


        //将这个数据封装到一个Fruit类中
        fruitDAO.addFruit(new Fruit(0,fname,price,fcount,remark));

        response.sendRedirect("fruit.do");




    }


    //默认的组件
    private void index(HttpServletRequest request , HttpServletResponse response)throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession() ;
        //默认显示第一页
        Integer pageNo = 1 ;

        String keyword = null;
        //获取隐藏域里的值
        String oper = request.getParameter("oper");
        //如果这个值是null 并且为search说明这个请求是从表单来的
        if(StringUtils.isNotEmpty(oper) && "search".equals(oper)){
            //去查相应的数据
            //如果oper!=null 说明 通过表单的查询按钮点击过来的
            //如果oper是空的，说明 不是通过表单的查询按钮点击过来的
            pageNo = 1 ;
            keyword = request.getParameter("keyword");
            //如果keyword为null，需要设置为空字符串""，否则查询时会拼接成 %null% , 我们期望的是 %%
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
            //如果不是点击的查询按钮，那么查询是基于session中保存的现有keyword进行查询
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
