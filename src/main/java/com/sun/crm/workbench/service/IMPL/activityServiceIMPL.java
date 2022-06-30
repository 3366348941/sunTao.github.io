package com.sun.crm.workbench.service.IMPL;

import com.sun.crm.workbench.Mapper.ActivityMapper;
import com.sun.crm.workbench.pojo.Activity;
import com.sun.crm.workbench.service.activityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class activityServiceIMPL implements activityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public int saveActivity(Activity activity) {
        return activityMapper.saveActivity(activity);
    }

    @Override
    public List<Activity> queryAllActivity() {
        return activityMapper.queryAllActivity();
    }
}
