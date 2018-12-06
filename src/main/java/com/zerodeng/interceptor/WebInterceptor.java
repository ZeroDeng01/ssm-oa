package com.zerodeng.interceptor;

import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * @ClassName:WebInterceptor
 * @Description:TODO 拦截器
 * @Author:ZeroDeng
 * @Date:2018-12-04 17:29
 * @Version:1.0
 **/

public class WebInterceptor  implements HandlerInterceptor {
    private static Logger logger = Logger.getLogger(WebInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getSession().getAttribute("user")==null) {
            //回到登陆页面
            logger.info("用户没登陆");
            request.getRequestDispatcher("login.do").forward(request, response);
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
