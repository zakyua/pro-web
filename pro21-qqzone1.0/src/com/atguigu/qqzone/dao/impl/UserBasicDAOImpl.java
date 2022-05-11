package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.qqzone.dao.UserBasicDAO;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @author ccstart
 * @create 2022-04-15 14:21
 */
public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {
    //根据账号和密码获取特定的用户信息
    @Override
    public UserBasic getUsBasic(String loginId, String pwd) {
        String sql ="SELECT * FROM t_user_basic WHERE loginId = ? AND pwd = ?";
        return super.load(sql,loginId,pwd);
    }

    //获取指定用户的所有好友
    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) {
        //在这里只是查出了当前这个人的好友的id，其他属性还没有值
        String sql ="select fid as id from t_friend where uid = ?";
        return super.executeQuery(sql,userBasic.getId());
    }

    //根据指定的id查询用户的信息
    @Override
    public UserBasic getUserBasicById(Integer id) {
        String sql = "select * from t_user_basic where id =?";
        return super.load(sql,id);
    }
}
