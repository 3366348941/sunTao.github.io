package com.sun.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class workbenchIndexController {

    @RequestMapping(value = "workbench/index.do")
    public String index(){
        return "workbench/index";
    }
}