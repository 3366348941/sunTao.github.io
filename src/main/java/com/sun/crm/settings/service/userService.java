package com.sun.crm.settings.service;

import com.sun.crm.settings.pojo.User;

import java.util.List;
import java.util.Map;

public interface userService {

    //这里需要一个通过map集合封装的账户、密码查询数据库表格user中的用户信息
    public User queryUserByUserActAndUserPwd(Map<String,Object> map);
    public List<User> queryAllUsers();

    User queryById(String UserId);
}
