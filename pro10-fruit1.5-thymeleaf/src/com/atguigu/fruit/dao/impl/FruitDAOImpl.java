package com.atguigu.fruit.dao.impl;



import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.basedao.BaseDAO;
import com.atguigu.myssm.util.StringUtils;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }

    @Override
    public Fruit getFruitByid(Integer fid) {
        String sql = "select * from t_fruit where fid=?";
        return super.load(sql,fid);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname = ? , price = ? ,fcount = ?, remark =? where fid =?";
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
    }

    @Override
    public void delFruit(Integer fid) {
        String sql = "delete from t_fruit where fid = ?";
        super.executeUpdate(sql,fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql ="insert into t_fruit values(0,?,?,?,?)";
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());


    }

}
