package com.sun.crm.workbench.service;

import com.sun.crm.workbench.pojo.Activity;

import java.util.List;

public interface activityService {

    //保存市场活动的功能
    int  saveActivity(Activity activity);

    //查询所有市场活动
    List<Activity> queryAllActivity();

}
