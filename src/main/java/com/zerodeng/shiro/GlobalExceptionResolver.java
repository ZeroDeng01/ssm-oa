package com.zerodeng.shiro;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName:GlobalExceptionResolver
 * @Description:TODO 全局异常处理
 * @Author:ZeroDeng
 * @Date:2018-12-11 9:36
 * @Version:1.0
 **/
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    private static final Logger log = Logger.getLogger(GlobalExceptionResolver.class);// 日志文件

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if((ex instanceof AuthenticationException) || (ex instanceof UnauthorizedException)){
            //未授权401
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

        if(isAjaxRequestInternal(request, response)){
            ModelAndView mv = new ModelAndView();
            /*使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常   */
            FastJsonJsonView view = new FastJsonJsonView();
            Map<String, Object> attributes = new HashMap<String, Object>();
            attributes.put("code", "1000001");
            attributes.put("msg", ex.getMessage());
            view.setAttributesMap(attributes);
            mv.setView(view);
            log.debug("异常:" + ex.getMessage(), ex);
            return mv;
        }else{
            Map<String, Object> model = new ConcurrentHashMap<String, Object>();
            model.put("ex", ex);
            // 可以细化异常信息，给出相应的提示
            log.info("==========发生了异常：");
            log.info("==========异常类型：" + ex.getClass().getSimpleName());
            log.info("==========异常描述：" + ex.getMessage());
            log.info("==========异常原因：" + ex.getCause());
            return new ModelAndView("Msg", model);
        }
    }

    /**
     * 判断Request是否为Ajax请求
     * @param request
     * @param response
     * @return
     */
    private boolean isAjaxRequestInternal(HttpServletRequest request, HttpServletResponse response) {
        return (request.getHeader("X-Requested-With") != null &&"XMLHttpRequest".equals( request.getHeader("X-Requested-With").toString()) ) ;
    }
}