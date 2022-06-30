package com.sun.crm.commons.pojo;

import org.springframework.stereotype.Component;

/**
 * 用于通用的返回json对象
 */
@Component("returnObject")
public class returnObject {

    private String code;      //code---1  标识登录成功， code---0  标识当前登录失败
    private String massage;   //massage   记录当前失败原因
    private Object otherObj;  //其他内容


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Object getOtherObj() {
        return otherObj;
    }

    public void setOtherObj(Object otherObj) {
        this.otherObj = otherObj;
    }
}
