package com.buaa.sn2ov.other;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by SN2OV on 2016/3/24.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());

//        String userName = (String)request.getSession().getAttribute("userName");
//        if(userName == null){
//            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request,response);
//            return false;
//        }else
            return true;
    }
}

