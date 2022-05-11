package com.atguigu.myssm.ioc;

/**
 * @author chen
 * @create 2022-04-12-20:19
 */
public interface BeanFactory {
    //根据id找到一个bean
    Object getBean(String id);
}
