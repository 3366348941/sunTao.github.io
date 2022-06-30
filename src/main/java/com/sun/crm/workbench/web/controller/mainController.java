package com.sun.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainController {

    /**
     * 通过该方法去访问WEB-INF目录受保护下的资源
     * @return
     */
    @RequestMapping("workbench/main/index.do")
    public String index(){
        return "workbench/main/index";
    }

}
