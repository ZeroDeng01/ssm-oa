package com.zerodeng.controller.system.Modules;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zerodeng.bean.system.SystemModules;
import com.zerodeng.controller.Index;
import com.zerodeng.service.system.Modules.SystemModulesService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName:Modules
 * @Description:TODO 模块设置
 * @Author:ZeroDeng
 * @Date:2018-12-06 11:52
 * @Version:1.0
 **/
@Controller
@RequestMapping("/System/Modules")
public class Modules {
    private static Logger logger = Logger.getLogger(Modules.class);

    @Resource
    private SystemModulesService systemModulesService;
    /**
    * @Author ZeroDeng
    * @Description TODO 打开模块列表
    * @Date 16:40 2018-12-06
    * @Param [pageNo, pageSize]
    * @return org.springframework.web.servlet.ModelAndView
    * @Version 1.0
    **/
    @RequestMapping(value = "ModulesList",method = RequestMethod.GET)
    public ModelAndView ModulesList(HttpServletRequest request, HttpServletResponse response,@RequestParam("pageNo")int pageNo){
        ModelAndView modelAndView = new ModelAndView("WEB-INF/jsp/system/modules/modulesList");
        //初始化index用户信息
        modelAndView = Index.initIndex(request,response,modelAndView);
        //本模块激活状态
        modelAndView.addObject("ModulesActive","sub-active");
        //父级菜单激活状态
        modelAndView.addObject("ModulesModulesActive","active");
        //父级菜单展开状态
        modelAndView.addObject("ModulesModulesOpen","display: block;");

        PageHelper.startPage(pageNo, 5);
        //返回所有模块信息
        List<SystemModules> MosulesList = systemModulesService.selectAll();
        PageInfo page = new PageInfo(MosulesList);


        modelAndView.addObject("List",MosulesList);
        modelAndView.addObject("Page",page);
        return modelAndView;
    }
}
