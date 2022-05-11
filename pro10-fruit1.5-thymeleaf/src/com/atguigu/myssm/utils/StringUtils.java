package com.atguigu.myssm.util;

/**
 * @author chen
 * @create 2022-04-10-16:27
 */
public class StringUtils {

    //判断一个字符串是否为 null或  ""
    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }


    public static boolean isNotEmpty(String str){
       return !isEmpty(str);
    }
}
