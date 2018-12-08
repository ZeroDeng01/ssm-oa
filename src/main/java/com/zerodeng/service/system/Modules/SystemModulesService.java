package com.zerodeng.service.system.Modules;

import com.zerodeng.bean.system.SystemModules;

import java.util.List;

/**
 * @ClassName:SystemModulesService
 * @Description:TODO
 * @Author:ZeroDeng
 * @Date:2018-12-06 14:07
 * @Version:1.0
 **/
public interface SystemModulesService {
    /**
    * @Author ZeroDeng
    * @Description TODO 查询所有模块信息集合
    * @Date 14:15 2018-12-06
    * @Param []
    * @return java.util.List<com.zerodeng.bean.system.SystemModules>
    * @Version 1.0
    **/
    public List<SystemModules> selectAll();
    /**
    * @Author ZeroDeng
    * @Description TODO 查询所有非按钮的菜单信息
    * @Date 18:21 2018-12-07
    * @Param []
    * @return java.util.List<com.zerodeng.bean.system.SystemModules>
    * @Version 1.0
    **/
    public  List<SystemModules> selectAllNotMenu();
    /**
    * @Author ZeroDeng
    * @Description TODO 添加模块信息
    * @Date 18:54 2018-12-07
    * @Param [modules]
    * @return int
    * @Version 1.0
    **/
    public int insertModules(SystemModules modules);
    /**
    * @Author ZeroDeng
    * @Description TODO 删除模块信息
    * @Date 23:53 2018-12-07
    * @Param [id]
    * @return int
    * @Version 1.0
    **/
    public int deleteById(int id);
    /**
    * @Author ZeroDeng
    * @Description TODO 查询指定模块信息
    * @Date 0:30 2018-12-08
    * @Param [id]
    * @return com.zerodeng.bean.system.SystemModules
    * @Version 1.0
    **/
    public SystemModules selectByPrimaryKey(Long id);
    /**
    * @Author ZeroDeng
    * @Description TODO 修改模块信息
    * @Date 1:09 2018-12-08
    * @Param [record]
    * @return int
    * @Version 1.0
    **/
    public int updateByPrimaryKeySelective(SystemModules record);
}
