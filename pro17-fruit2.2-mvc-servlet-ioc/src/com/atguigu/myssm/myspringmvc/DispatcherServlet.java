package com.atguigu.myssm.myspringmvc;

import com.atguigu.myssm.ioc.BeanFactory;
import com.atguigu.myssm.ioc.ClassPathXmlApplicationContext;
import com.atguigu.myssm.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author chen
 * @create 2022-04-11-14:37
 */
//让这个类拦截所有的以.do的请求
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    BeanFactory beanFactory = new ClassPathXmlApplicationContext();

    public DispatcherServlet(){
    }

    public void init() throws ServletException {
        super.init();

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        //假设url是：  http://localhost:8080/pro15/hello.do
        //那么servletPath是：    /hello.do
        // 我的思路是：
        // 第1步： /hello.do ->   hello   或者  /fruit.do  -> fruit
        // 第2步： hello -> HelloController 或者 fruit -> FruitController
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDotIndex = servletPath.lastIndexOf(".do") ;
        servletPath = servletPath.substring(0,lastDotIndex);

        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operate = request.getParameter("operate");
        if(StringUtils.isEmpty(operate)){
            operate = "index" ;
        }


        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method:methods ) {
                //在这里就找到了相应的组件了
                if(operate.equals(method.getName())){
                    //1.获取当前方法的形参
                    Parameter[] parameters = method.getParameters();
                    //准备一个数组来存放参数值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        //将每一个形参取出来
                        Parameter parameter = parameters[i];
                        //获取这个形参的名称(带着类型的名称)
                        String parameterName = parameter.getName();
                        //如果参数名是request,response,session 那么就不是通过请求中获取参数的方式了
                        if("request".equals(parameterName)){
                            parameterValues[i] = request ;
                        }else if("response".equals(parameterName)){
                            parameterValues[i] = response ;
                        }else if("session".equals(parameterName)){
                            parameterValues[i] = request.getSession() ;
                        }else{
                            //从请求中获取参数值
                            String parameterValue = request.getParameter(parameterName);

                            //获取参数的类型
                            String typeName = parameter.getType().getName();

                            Object parameterObj = parameterValue ;

                            if(parameterObj!=null) {
                                if ("java.lang.Integer".equals(typeName)) {
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }

                            parameterValues[i] = parameterObj ;
                        }

                    }


                    //2.controller中组件的调用
                    method.setAccessible(true);
                    Object returnobj = method.invoke(controllerBeanObj,parameterValues);
                    String  methodReturnStr = (String)returnobj;


                    //3.视图
                    if( methodReturnStr.startsWith("redirect:") ){  //比如：redirect：fruit.do
                        //需要服务器重定向
                        String redirectStr = methodReturnStr.substring("redirect:".length());

                        response.sendRedirect(redirectStr);//在这里再去请求开始时的那个页面/index/html
                    }else {
                        //说明是需要服务器内部转发的
                        super.processTemplate(methodReturnStr,request,response);
                    }
                }



                }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
