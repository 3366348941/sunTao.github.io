package com.sun.crm.commons.utils;

import java.util.UUID;

public class randomUUID {
    public static String createId(){
        //创建javaSE自带的工具类
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
