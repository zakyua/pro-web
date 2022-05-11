package com.atguigu.fruit.dao;





import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    //获取指定页面上库存列表信息，每一页5条
    List<Fruit> getFruitList(Integer pagNo);

    //根据指定id获取水果的信息
    Fruit getFruitByid(Integer fid);


    //根据id去修改指定的一条记录
    void updateFruit(Fruit fruit);

    //删除一条记录
    void delFruit(Integer fid);

    //添加一条记录
    void addFruit(Fruit fruit);


    //获取表中的总记录的条数
    int getFruitCount();




}
