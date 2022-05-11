package com.atguigu.fruit.exceptions;

/**
 * @author chen
 * @create 2022-04-13-18:03
 */
public class FruitDAOException extends RuntimeException {
    public FruitDAOException(String msg){
        super(msg);
    }
}
