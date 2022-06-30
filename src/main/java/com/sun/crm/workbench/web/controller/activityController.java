package com.sun.crm.workbench.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.crm.commons.pojo.returnObject;
import com.sun.crm.commons.utils.DateUtils;
import com.sun.crm.commons.utils.constants;
import com.sun.crm.commons.utils.randomUUID;
import com.sun.crm.settings.pojo.User;
import com.sun.crm.settings.service.userService;
import com.sun.crm.workbench.pojo.Activity;
import com.sun.crm.workbench.service.activityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class activityController {

    @Autowired
    private userService userService;
    @Autowired
    private activityService activityService;

    /**
     * 动态显示市场活动页面
     * @param request
     * @return
     */
    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        //1.查询到所有用户信息
        List<User> userList = userService.queryAllUsers();
        //2.将信息保存到request域中
        request.setAttribute("userList",userList);



        //在进行活动列表查询的之前， 进行分页操作！
        //pageNum 页号      pageSize页面的条数长度
        PageHelper.startPage((1-1)*2,4);
        //1.通过service查询所有市场活动信息
        List<Activity> activityList = activityService.queryAllActivity();
        //插播一下：存到request域对象之前，将Activity对象中的owner更换为名字
        for (Activity activity : activityList) {
            //1.获取owner【这里存储的是用户id】
            String owner = activity.getOwner();
            //2.查询act表格将该属性设置为名字
            User user = userService.queryById(owner);
            activity.setOwner(user.getName());
        }


        //获取分页所需参数
        PageInfo<Activity> activityPageInfo = new PageInfo<>(activityList);
        //获取共计多少条记录
        long size = activityPageInfo.getTotal();
        //获取共计多少页
        long pages = activityPageInfo.getPages();
        //当前显示当前页号
        int pageNum = activityPageInfo.getPageNum();


        //2.保存至request域
        request.setAttribute("activityList",activityList);
        //3.请求转发至：workbench/activity/index
        return "workbench/activity/index";
    }


    /**
     * 最终返回的页面还是市场活动主页，且该方法是回写数据
     * @param activity
     * @return
     */
    @RequestMapping("/workbench/activity/saveActivity.do")
    @ResponseBody
    public Object saveActivity(Activity activity, HttpSession session){
        //1.补从写入数据【因请求数据只有6个，少了3个，额外的修改字段首次新建活动不写入】
        //1.1通过uuid获取32为字符作为市场活动id值
        activity.setId(randomUUID.createId());
        //1.2写入时间
        activity.setCreateTime(DateUtils.formatDateTimeSec(new Date()));
        //1.3写入人员，从session中获取
        User attribute = (User) session.getAttribute(constants.SESSION_USER);
        activity.setCreateBy(attribute.getName());

        //2.调用service方法，执行写入，判断是否成功写入，并通过returnObject对象传给ajax
        returnObject returnObject = new returnObject();
        try{
        int i = activityService.saveActivity(activity);
        if(i>0){
            //成功写入
            returnObject.setCode(constants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMassage("成功保存！");
        }else{
            //写入失败
            returnObject.setCode(constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMassage("系统正忙，请您稍后在试..");
        }
        }catch(Exception e){
            //写入失败
            returnObject.setCode(constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMassage("系统正忙，请您稍后在试..");
        }

        return returnObject;
    }







}
