package com.gdou.gas.interceptor;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.gdou.gas.util.CodeMsg;
import com.gdou.gas.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



/**
 * 后台登录拦截器
 *
 */
@Component
public class LoginInterceptor implements HandlerInterceptor{



    @Override
    public boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute("user");
        Object home_user = session.getAttribute("home_user");
        if(attribute == null){
            //首先判断是否是ajax请求
            if(StringUtil.isAjax(request)){
                //表示是ajax请求
                response.setCharacterEncoding("UTF-8");
                try {
                    response.getWriter().write(JSON.toJSONString(CodeMsg.USER_SESSION_EXPIRED));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
            //说明是普通的请求，可直接重定向到登录页面
            //用户还未登录或者session失效,重定向到登录页面
            try {
                response.sendRedirect("/system/login");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return false;
        }

        return true;
    }
}