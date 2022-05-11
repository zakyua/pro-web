package com.atguigu.myssm.myspringmvc;

/**
 * @author ccstart
 * @create 2022-04-15 17:03
 */
public class PageController {

    //page.do?operate=page&page=frames/left
    //page是方法 String page是指frames/left
    public String page(String page){
        return page;//返回frames/left给中央控制器
    }


}
