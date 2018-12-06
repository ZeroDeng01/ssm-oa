package com.zerodeng.service.system.Modules;

import com.zerodeng.bean.system.SystemModules;
import org.apache.log4j.Logger;

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
}
