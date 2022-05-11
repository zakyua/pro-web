package com.atguigu.myssm.filters;

import com.atguigu.myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author chen
 * @create 2022-04-13-16:55
 */
@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        try{
            //开启事务(不让自动提交针对DMLcaoz)
            TransactionManager.beginTrans();
            //放行
            filterChain.doFilter(servletRequest,servletResponse);
            //提交
            TransactionManager.commit();

        }catch (Exception e){
            e.printStackTrace();
            //如果让捕捉到,回滚
            try {
                TransactionManager.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void destroy() {

    }
}
