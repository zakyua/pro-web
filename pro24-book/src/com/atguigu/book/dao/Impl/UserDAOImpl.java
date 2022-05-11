package com.atguigu.book.dao.Impl;


import com.atguigu.book.dao.UserDAO;
import com.atguigu.book.pojo.User;
import com.atguigu.myssm.basedao.BaseDAO;

/**
 * @author ccstart
 * @create 2022-04-19 11:08
 */
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

    //根据用户名，密码查找用户
    @Override
    public User getUser(String uname, String pwd) {
        String sql="SELECT * FROM t_user WHERE uname = ? AND pwd = ?";
        return super.load(sql,uname,pwd);
    }
}
