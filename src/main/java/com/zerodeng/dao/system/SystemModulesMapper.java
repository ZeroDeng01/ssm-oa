package com.zerodeng.dao.system;

import com.zerodeng.bean.system.SystemModules;

import java.util.List;

public interface SystemModulesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemModules record);

    int insertSelective(SystemModules record);

    SystemModules selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemModules record);

    int updateByPrimaryKey(SystemModules record);

    List<SystemModules> selectAll();

    List<SystemModules> selectAllNotMenu();

    int deleteById(int id);
}