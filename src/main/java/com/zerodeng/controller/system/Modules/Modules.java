package com.zerodeng.controller.system.Modules;

import com.zerodeng.bean.system.SystemModules;
import com.zerodeng.bean.system.SystemUsers;
import com.zerodeng.service.system.Modules.SystemModulesService;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;

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
    * @Description TODO 打开模块设置页面
    * @Date 16:17 2018-12-07
    * @Param [request, response]
    * @return org.springframework.web.servlet.ModelAndView
    * @Version 1.0
    **/
    @RequestMapping(value = "ModulesList",method = RequestMethod.GET)
    //@RequiresRoles({"123456"})
    public String ModulesList(HttpServletRequest request, HttpServletResponse response,Model model){
        return "WEB-INF/jsp/page/system/modules/modulesList";
    }

    /**
    * @Author ZeroDeng
    * @Description TODO 获取模块信息
    * @Date 16:40 2018-12-06
    * @Param [pageNo, pageSize]
    * @return org.springframework.web.servlet.ModelAndView
    * @Version 1.0
    **/
    @RequestMapping(value = "getModulesList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getModulesList(HttpServletRequest request, HttpServletResponse response){

        //PageHelper.startPage(pageNo, 5);
        //返回所有模块信息
        //List<SystemModules> MosulesList = systemModulesService.selectAll();
        //PageInfo page = new PageInfo(MosulesList);
        HashMap<String,Object> map = new HashMap<String,Object>();
        List<SystemModules> MosulesList = systemModulesService.selectAll();
        map.put("code",0);
        map.put("count",MosulesList.size());
        map.put("msg","success");
        map.put("data",MosulesList);
        String jsonString = JSON.toJSONString(map);
        return jsonString;
    }

    /**
    * @Author ZeroDeng
    * @Description TODO 打开模块添加页面
    * @Date 17:15 2018-12-07
    * @Param []
    * @return org.springframework.web.servlet.ModelAndView
    * @Version 1.0
    **/
    @RequestMapping(value = "modulesAddPage",method = RequestMethod.GET)
    public String modulesAddPage(Model model){
        List<SystemModules> ModulesList = systemModulesService.selectAllNotMenu();
        model.addAttribute("list",ModulesList);
        return "WEB-INF/jsp/page/system/modules/modulesAdd";
    }


    /**
    * @Author ZeroDeng
    * @Description TODO 添加模块数据
    * @Date 23:56 2018-12-07
    * @Param [request, response, authorityName, authority, menuUrl, menuIcon, isMenu, orderNumber, parentId]
    * @return java.lang.String
    * @Version 1.0
    **/
    @RequestMapping(value = "modulesAdd" ,method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String modulesAdd(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("authorityName") String authorityName,
                             @RequestParam("authority") String authority,
                             @RequestParam("menuUrl") String menuUrl,
                             @RequestParam("menuIcon") String menuIcon,
                             @RequestParam("isMenu") int isMenu,
                             @RequestParam("orderNumber") int orderNumber,
                             @RequestParam("parentId") long parentId)
    {
        HttpSession session = request.getSession();
        SystemUsers user = (SystemUsers)session.getAttribute("user");
        java.util.Date  date=new java.util.Date();
        java.sql.Date  sqldate=new java.sql.Date(date.getTime());

        SystemModules systemModules = new SystemModules();
        systemModules.setAuthorityname(authorityName);
        systemModules.setAuthority(authority);
        systemModules.setMenuurl(menuUrl);
        systemModules.setMenuicon(menuIcon);
        systemModules.setIsmenu(isMenu);
        systemModules.setOrdernumber(orderNumber);
        systemModules.setParentid(parentId);
        systemModules.setCreateUser(user.getId());
        systemModules.setCreateTime(sqldate);
        systemModules.setStatus(0);
        int status = systemModulesService.insertModules(systemModules);
        HashMap<String,Object> map = new HashMap<String,Object>();
        if(status>0){
            map.put("code",200);
            map.put("msg","添加成功");
        }else{
            map.put("code",2001);
            map.put("msg","添加失败");
        }
        String json = JSON.toJSONString(map);
        return json;
    }

    /**
    * @Author ZeroDeng
    * @Description TODO 删除模板数据
    * @Date 0:25 2018-12-08
    * @Param [request, response, id]
    * @return java.lang.String
    * @Version 1.0
    **/
    @RequestMapping(value = "modulesDel" ,method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String modulesDel(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("id") int id)
    {
        int status = systemModulesService.deleteById(id);
        HashMap<String,Object> map = new HashMap<String,Object>();
        if(status>0){
            map.put("code",200);
            map.put("msg","删除成功");
        }else{
            map.put("code",2001);
            map.put("msg","删除失败");
        }
        String json = JSON.toJSONString(map);
        return json;
    }

    /**
    * @Author ZeroDeng
    * @Description TODO 打开修改页面
    * @Date 0:28 2018-12-08
    * @Param [id]
    * @return org.springframework.web.servlet.ModelAndView
    * @Version 1.0
    **/
    @RequestMapping(value = "modulesModPage",method = RequestMethod.GET)
    public String modulesModPage(@RequestParam("id") long id,Model model)
    {
        SystemModules systemModules = systemModulesService.selectByPrimaryKey(id);
        model.addAttribute("id",systemModules.getId());
        model.addAttribute("authorityName",systemModules.getAuthorityname());
        model.addAttribute("authority",systemModules.getAuthority());
        model.addAttribute("menuUrl",systemModules.getMenuurl());
        model.addAttribute("menuIcon",systemModules.getMenuicon());
        model.addAttribute("isMenu",systemModules.getIsmenu());
        model.addAttribute("orderNumber",systemModules.getOrdernumber());
        model.addAttribute("parentId",systemModules.getParentid());
        List<SystemModules> ModulesList = systemModulesService.selectAllNotMenu();
        model.addAttribute("list",ModulesList);
        return "WEB-INF/jsp/page/system/modules/modulesMod";
    }

    /**
     * @Author ZeroDeng
     * @Description TODO 修改模块数据
     * @Date 23:56 2018-12-07
     * @Param [request, response, authorityName, authority, menuUrl, menuIcon, isMenu, orderNumber, parentId]
     * @return java.lang.String
     * @Version 1.0
     **/
    @RequestMapping(value = "modulesMod" ,method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String modulesMod(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("id") long id,
                             @RequestParam("authorityName") String authorityName,
                             @RequestParam("authority") String authority,
                             @RequestParam("menuUrl") String menuUrl,
                             @RequestParam("menuIcon") String menuIcon,
                             @RequestParam("isMenu") int isMenu,
                             @RequestParam("orderNumber") int orderNumber,
                             @RequestParam("parentId") long parentId)
    {
        HttpSession session = request.getSession();
        SystemUsers user = (SystemUsers)session.getAttribute("user");
        java.util.Date  date=new java.util.Date();
        java.sql.Date  sqldate=new java.sql.Date(date.getTime());

        SystemModules systemModules = new SystemModules();
        systemModules.setId(id);
        systemModules.setAuthorityname(authorityName);
        systemModules.setAuthority(authority);
        systemModules.setMenuurl(menuUrl);
        systemModules.setMenuicon(menuIcon);
        systemModules.setIsmenu(isMenu);
        systemModules.setOrdernumber(orderNumber);
        systemModules.setParentid(parentId);
        systemModules.setModifyUser(user.getId());
        systemModules.setModifyTime(sqldate);
        systemModules.setStatus(0);
        int status = systemModulesService.updateByPrimaryKeySelective(systemModules);
        HashMap<String,Object> map = new HashMap<String,Object>();
        if(status>0){
            map.put("code",200);
            map.put("msg","修改成功");
        }else{
            map.put("code",2001);
            map.put("msg","修改失败");
        }
        String json = JSON.toJSONString(map);
        return json;
    }
}
