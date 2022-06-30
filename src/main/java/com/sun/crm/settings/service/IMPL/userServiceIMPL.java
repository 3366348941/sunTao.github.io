package com.sun.crm.settings.service.IMPL;

import com.sun.crm.settings.mapper.UserMapper;
import com.sun.crm.settings.pojo.User;
import com.sun.crm.settings.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class userServiceIMPL implements userService {


    //注入sql映射
    @Autowired
    private UserMapper mapper;

    /**
     * 这里需要一个通过map集合封装的账户、密码查询数据库表格user中的用户信息
     * @param map 前端请求数据使用map进行的一个封装
     * @return
     */
    @Override
    public User queryUserByUserActAndUserPwd(Map<String, Object> map) {
        User user = mapper.queryUserByUserActAndUserPwd(map);
        return user;
    }

    @Override
    public List<User> queryAllUsers() {
        return mapper.queryAllUsers();
    }

    @Override
    public User queryById(String UserId) {
        return mapper.queryById(UserId);
    }
}
