package com.zerodeng.controller;

import com.zerodeng.bean.system.SystemUsers;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName:Index
 * @Description:TODO 主页
 * @Author:ZeroDeng
 * @Date:2018-12-04 17:08
 * @Version:1.0
 **/
@Controller
public class Index {
    private static Logger logger = Logger.getLogger(Index.class);

    /**
    * @Author ZeroDeng
    * @Description TODO 主页
    * @Date 11:28 2018-12-06
    * @Param [request, response]
    * @return org.springframework.web.servlet.ModelAndView
    * @Version 1.0
    **/
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public ModelAndView Index(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("WEB-INF/jsp/index");
        HttpSession session = request.getSession();
        SystemUsers user = (SystemUsers)session.getAttribute("user");
        modelAndView.addObject("UserName",user.getName());
        return modelAndView;
    }

    /**
    * @Author ZeroDeng
    * @Description TODO 设置主题
    * @Date 14:13 2018-12-07
    * @Param []
    * @return org.springframework.web.servlet.ModelAndView
    * @Version 1.0
    **/
    @RequestMapping(value = "setTheme",method = RequestMethod.GET)
    public ModelAndView setTheme(){
        ModelAndView modelAndView = new ModelAndView("WEB-INF/jsp/common/setTheme");
        return modelAndView;
    }

    /**
    * @Author ZeroDeng
    * @Description TODO 打开消息选项卡
    * @Date 14:31 2018-12-07
    * @Param []
    * @return org.springframework.web.servlet.ModelAndView
    * @Version 1.0
    **/
    @RequestMapping(value = "messagePage",method = RequestMethod.GET)
    public ModelAndView messagePagge(){
        ModelAndView modelAndView = new ModelAndView("WEB-INF/jsp/common/message");
        return modelAndView;
    }

    /**
    * @Author ZeroDeng
    * @Description TODO 打开修改密码选项卡
    * @Date 14:32 2018-12-07
    * @Param []
    * @return org.springframework.web.servlet.ModelAndView
    * @Version 1.0
    **/
    @RequestMapping(value = "setPasswordPage",method = RequestMethod.GET)
    public ModelAndView setPasswordPage(){
        ModelAndView modelAndView = new ModelAndView("WEB-INF/jsp/common/password");
        return modelAndView;
    }

}
