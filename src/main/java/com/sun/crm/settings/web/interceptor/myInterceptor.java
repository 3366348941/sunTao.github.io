package com.sun.crm.settings.web.interceptor;

import com.sun.crm.commons.utils.constants;
import com.sun.crm.settings.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class myInterceptor implements HandlerInterceptor {
    /**
     * controller中目标方法执行前，执行该方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return  结果是boolean,true  允许当前controller方法的执行以及访问，false相反
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
       //1.通过request请求域对象，获取session
        HttpSession session = httpServletRequest.getSession();
        //2.然后获取指定属性名的session
        User attribute = (User) session.getAttribute(constants.SESSION_USER);
        //3.然后查询该User
        if("".equals(attribute) || attribute==null){
            //当前用户未登录：跳转页面，禁止通行
            //牵扯到跳转什么页面：
            //（1）请求转发：一次请求，且转发后的资源必须是原请求url下级目录的资源，否则会报错；
            //     且地址栏不发生变化。
            //（2）重定向：两次请求，服务器接收用户请求后，无法完成当前请求，且给一个权限url通
            //    过response对象交给客户端，客户端自动进行再次访问，地址栏也会发送变化。
            // (3) 自己手动重定向，必须添加项目名称
            // (4) 项目名称可以通过动态获取
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
            return false;
        }else
            //当前用户已登录：放行
            return true;




    }

    /**
     * 目标方法执行后，视图返回之前，执行
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 所有请求工作完成处理，执行该方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
