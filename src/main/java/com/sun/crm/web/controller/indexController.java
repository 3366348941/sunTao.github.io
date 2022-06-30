package com.sun.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    //

    /**
     * 方法解决需求：
     * 用户访问：http://127.0.0.1:8080/crm/  跳转登录页面
     * 跳转页面两种方式：请求转发， 重定向
     * 路径问题：
     * 理论上：个controller上分配url的写法是：http://127.0.0.1:8080/crm/
     * 为了简化开发，前面的协议：http://ip:port/项目名  这部分全部省略 用斜杠：/
     * 代表应用根目录下的斜杠
     * @return
     */
    @RequestMapping(value = "/")
    public String welcomePage(){
        //视图解析器已配置，这里写资源名即可.
        return "index";
    }




}
