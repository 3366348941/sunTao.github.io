package com.sun.crm.settings.web.controller;

import com.sun.crm.commons.pojo.returnObject;
import com.sun.crm.commons.utils.DateUtils;
import com.sun.crm.commons.utils.constants;
import com.sun.crm.settings.pojo.User;
import com.sun.crm.settings.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
public class userController {

    //注入service
    @Autowired
    private userService userService;

    //用作返回对象使用，该对象后续可交给spring
    @Autowired
    private returnObject returnObject;


    /**
     *进入用户登录页面！
     * @return
     */
    @RequestMapping("/settings/qx/user/toLogin.do")//url要和controller方法处理完后，响应信息返回的页面资源目录保持一致
    public String toLogin(){

        return "settings/qx/user/login";
    }


    /**
     * 登录验证
     * @param userAct
     * @param userPwd
     * @param isRemPwd
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping(value = "/settings/qx/user/doLoin.do")//该方法最后返回的视图资源依旧是login.jsp，故事当前视图的路径
    @ResponseBody//当前方法，只做回写数据。因spring-MVC配置文件中添加了注解驱动，则返回的类型均会通过底层组件转化为json在进行回写。
    public Object doLoin(String userAct, String userPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response,HttpSession session){

        System.out.println("user:"+userAct+"userPwd:"+isRemPwd);
        //1.创建map,且封装前台请求数据
        Map<String, Object> map = new HashMap<>();
        map.put("loginAct",userAct);
        map.put("loginPwd",userPwd);
        //2.调用service中的业务方法，查询结果
        User user = userService.queryUserByUserActAndUserPwd(map);

        //3. 验证账户是否可正常登录
        //3.1 当前实现和账号有效期进行核对
        String expireTime =null;
        if(user!=null){
            expireTime=user.getExpireTime();
        }
        //3.2获取当前日期，后续和账户有效期进行比较

        /*simpleDateFormat.applyPattern("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(new Date());*/

        if(user==null){
            //登录失败，账户或密码错误
            returnObject.setCode(constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMassage("账户或密码错误");

        }else if(expireTime.compareTo(DateUtils.formatDateTimeSec(new Date()))<0){
            //登录失败，账号已过期
            returnObject.setCode(constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMassage("账号已过期");
        }else if(user.getLockState().equals("0")){
            //登录失败，用户当前账号异常被锁定
            returnObject.setCode(constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMassage(constants.RETURN_OBJECT_CODE_FAIL);
        }else if(!user.getAllowIps().contains(request.getRemoteAddr())){
            //登录失败，ip受限
            returnObject.setCode(constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMassage("当前网络异常，ip受限");
        }else{
            //3.3  登录成功
            returnObject.setCode(constants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMassage("登录成功");
            //3.4  将登录成功的对象存储到session会话域中，记得在退出时，删除该session即可；
            session.setAttribute(constants.SESSION_USER,user);


            System.out.println("---------start开始-----------");
            //十天免登录：单选框勾选后
            System.out.println("复选框中的结果："+isRemPwd);
            if("true".equals(isRemPwd)){
                Cookie c1=new Cookie("loginAct",user.getLoginAct());
                c1.setMaxAge(10*24*60*60);
                response.addCookie(c1);
                Cookie c2=new Cookie("loginPwd",user.getLoginPwd());
                c2.setMaxAge(10*24*60*60);
                response.addCookie(c2);
            }else{
                //把没有过期cookie删除
                Cookie c1=new Cookie("loginAct","1");
                c1.setMaxAge(0);
                response.addCookie(c1);
                Cookie c2=new Cookie("loginPwd","1");
                c2.setMaxAge(0);
                response.addCookie(c2);
            }
            System.out.println("---------end结束-----------");
        }

        return returnObject;
    }



    //处理用户退出

    /**
     * 1.清除域对象信息，以及用户的cookie
     * 2.跳转login主页
     * @param request
     * @return
     */
    @RequestMapping(value = "/settings/qx/user/exit.do")
    public String exit(@Autowired  HttpServletRequest request,HttpServletResponse response,HttpSession session){
        //1.删除session
        session.invalidate();

        //3.通过request获取cookie
        Cookie[] cookies = request.getCookies();
        //4.遍历删除cookie
        for (Cookie cookie : cookies) {
          //1.设置cookie有效期为0，相当于删除cookie,为负数，则退出浏览器删除cookie,正数则是有效期截至事件
            cookie.setMaxAge(0);
            //2.发送cookie!不发送无法进行修改，相同参数名的cookie会进行覆盖，相当于修改
            response.addCookie(cookie);
        }
        //5.跳转login页面，一定要重定向，从更目录进行访问，因为index.jsp中href值是路径拼接写法，
        //  顾名思义就是当前请求路径和index.jsp中的href中的转发路径时拼接的！
        return "redirect:/";//底层是springmvc 进行的重定向，mvc底层也是对response.sendRedirect()进行了封装。
                            //这种写法底层和自己进行response对象重定向一样。知识被mvc进行了封装。
    }





}
