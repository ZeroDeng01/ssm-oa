package com.zerodeng.service.system.Modules.impl;

import com.zerodeng.bean.system.SystemModules;
import com.zerodeng.controller.system.Modules.Modules;
import com.zerodeng.dao.system.SystemModulesMapper;
import com.zerodeng.service.system.Modules.SystemModulesService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service("ModulesService")
public class SystemModulesImpl implements SystemModulesService{
    @Resource
    private SystemModulesMapper systemModulesMapper;
    /**
    * @Author ZeroDeng
    * @Description TODO 查询所有模块信息集合
    * @Date 14:15 2018-12-06
    * @Param []
    * @return java.util.List<com.zerodeng.bean.system.SystemModules>
    * @Version 1.0
    **/
    public List<SystemModules> selectAll(){
        return systemModulesMapper.selectAll();
    }
}
