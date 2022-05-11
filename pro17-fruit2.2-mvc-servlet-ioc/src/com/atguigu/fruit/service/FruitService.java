package com.atguigu.fruit.service;

import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author chen
 * @create 2022-04-12-19:43
 */
public interface FruitService {

    //获取指定页面的库存列表信息
    List<Fruit> getFruitList(String keyword,Integer pageNo);

    //添加库存信息
    void addFruit(Fruit fruit);

    //根据id查看指定库存记录
    Fruit getFruitByFid(Integer fid);

    //删除的特定的库存记录
    void delFruitByFid(Integer fid);

    //获取总页数
    Integer getPageCount(String keyword);

    //修改特定库存的数据
    void updateFruit(Fruit fruit);

}
