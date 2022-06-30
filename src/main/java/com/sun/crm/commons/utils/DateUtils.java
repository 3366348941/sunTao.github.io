package com.sun.crm.commons.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 针对日期工具类
 */
@Component("DateUtils")
public class DateUtils {


    //将对象设置为null
    private static  SimpleDateFormat simpleDateFormat=null;

    //然后制作一个获取对象的静态方法
    public static SimpleDateFormat getSimpleDateFormatInstances(){
        if(simpleDateFormat==null){
            synchronized (DateUtils.class){
                if(simpleDateFormat==null){
                    simpleDateFormat=new SimpleDateFormat();
                    return simpleDateFormat;
                }
            }
        }
        return simpleDateFormat;
    }

    /**
     * 按照：yyyy-MM-dd hh:mm:ss 格式生成字符串日期
     * @Date  用户填写新参日期类
     * @return
     */
    public static String formatDateTimeSec(Date date){
        SimpleDateFormat simpleDateFormatInstances = DateUtils.getSimpleDateFormatInstances();
        simpleDateFormatInstances.applyPattern("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(date);
        return format;
    }



    /**
     * 按照：yyyy-MM-dd  格式生成字符串日期
     * @Date  用户填写新参日期类
     * @return
     */
    public static String formatDate(Date date){
        SimpleDateFormat simpleDateFormatInstances = DateUtils.getSimpleDateFormatInstances();
        simpleDateFormatInstances.applyPattern("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        return format;
    }



}
