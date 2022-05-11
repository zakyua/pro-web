package com.atguigu.myssm.trans;

import com.atguigu.myssm.basedao.ConnUtil;

import java.nio.channels.Pipe;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author chen
 * @create 2022-04-13-17:06
 */
public class TransactionManager {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    //开启事务
    public static  void beginTrans() throws SQLException {

       ConnUtil.getCoon().setAutoCommit(false);
    }

    //提交事务
    public static void commit() throws SQLException {

        Connection coon = ConnUtil.getCoon();
        coon.commit();
        //如果提交事务了，说明没有在过滤器捕捉到异常，这个事务就完成了
        //我们就可以将threadLocal里的Connection移除
        ConnUtil.creatCoon();

    }


    //回滚事务
    public static void rollback() throws SQLException {
        Connection coon = ConnUtil.getCoon();
        coon.rollback();
        ConnUtil.creatCoon();
    }
}
