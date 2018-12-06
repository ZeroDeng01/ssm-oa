package com.zerodeng.controller;

import com.zerodeng.bean.system.SystemModules;
import com.zerodeng.bean.system.SystemUsers;
import com.zerodeng.service.system.Modules.SystemModulesService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

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
        modelAndView.addObject("indexActive","active");
        return modelAndView;
    }
    /**
    * @Author ZeroDeng
    * @Description TODO 初始化主页用户信息
    * @Date 19:51 2018-12-06
    * @Param [request, response, model]
    * @return org.springframework.web.servlet.ModelAndView
    * @Version 1.0
    **/
    public static ModelAndView initIndex(HttpServletRequest request, HttpServletResponse response,ModelAndView model){
        HttpSession session = request.getSession();
        SystemUsers user = (SystemUsers)session.getAttribute("user");
        model.addObject("UserName",user.getName());
        model.addObject("indexActive","active");
        return model;
    }


}
