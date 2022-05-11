package com.atguigu.myssm.filters;

import com.atguigu.myssm.util.StringUtils;
import com.mysql.fabric.xmlrpc.base.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author chen
 * @create 2022-04-13-16:38
 */
@WebFilter(urlPatterns = "*.do",initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class CharacterEndingFilter implements Filter {
    private String encoding = "utf-8";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String initParameter = filterConfig.getInitParameter("encoding");
        if(StringUtils.isNotEmpty(initParameter)){
            encoding = initParameter;
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =  (HttpServletRequest)servletRequest;
        request.setCharacterEncoding(encoding);
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
