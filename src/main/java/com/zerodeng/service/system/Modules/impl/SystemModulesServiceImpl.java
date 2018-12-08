package com.zerodeng.service.system.Modules.impl;

import com.zerodeng.bean.system.SystemModules;
import com.zerodeng.dao.system.SystemModulesMapper;
import com.zerodeng.service.system.Modules.SystemModulesService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service("ModulesService")
public class SystemModulesServiceImpl implements SystemModulesService{
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
    /**
    * @Author ZeroDeng
    * @Description TODO 查询所有非按钮的模块集合
    * @Date 18:55 2018-12-07
    * @Param []
    * @return java.util.List<com.zerodeng.bean.system.SystemModules>
    * @Version 1.0
    **/
    public  List<SystemModules> selectAllNotMenu(){
        return systemModulesMapper.selectAllNotMenu();
    }
    /**
    * @Author ZeroDeng
    * @Description TODO 添加模块信息
    * @Date 18:56 2018-12-07
    * @Param [modules]
    * @return int
    * @Version 1.0
    **/
    public int insertModules(SystemModules modules){
        return systemModulesMapper.insertSelective(modules);
    }
    /**
     * @Author ZeroDeng
     * @Description TODO 删除模块信息
     * @Date 23:53 2018-12-07
     * @Param [id]
     * @return int
     * @Version 1.0
     **/
    public int deleteById(int id){
        return systemModulesMapper.deleteById(id);
    }
    /**
    * @Author ZeroDeng
    * @Description TODO 查询指定模块信息
    * @Date 0:31 2018-12-08
    * @Param [id]
    * @return com.zerodeng.bean.system.SystemModules
    * @Version 1.0
    **/
    public SystemModules selectByPrimaryKey(Long id){
        return systemModulesMapper.selectByPrimaryKey(id);
    }
    /**
    * @Author ZeroDeng
    * @Description TODO 修改模块信息
    * @Date 1:10 2018-12-08
    * @Param [record]
    * @return int
    * @Version 1.0
    **/
    public int updateByPrimaryKeySelective(SystemModules record){
        return systemModulesMapper.updateByPrimaryKeySelective(record);
    }
}
