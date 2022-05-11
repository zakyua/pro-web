package com.atguigu.myssm.basedao;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author chen
 * @create 2022-04-13-17:00
 */
public class ConnUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();


    public  static final String DRIVER = "com.mysql.jdbc.Driver" ;
    public static final String URL = "jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public static final String USER = "root";
    public static final String PWD = "abc123" ;

    public static Connection creatCoon(){
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Connection getCoon(){

        Connection coon = threadLocal.get();
        if(coon == null){

            coon = creatCoon();
            threadLocal.set(coon);
        }

        return threadLocal.get();

    }
    public  static void closeCoon() throws SQLException {
        Connection coon = threadLocal.get();
        if(coon == null){
            return;
        }
       if(!coon.isClosed()){
           coon.close();
           threadLocal.set(null);
       }

    }


}
