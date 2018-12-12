package com.zerodeng.dao.system;

import com.zerodeng.bean.system.SystemUsers;
import org.apache.ibatis.annotations.Param;

public interface SystemUsersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemUsers record);

    int insertSelective(SystemUsers record);

    SystemUsers selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemUsers record);

    int updateByPrimaryKey(SystemUsers record);

    SystemUsers login(@Param("UserName")String UserName, @Param("Password")String Password);

    SystemUsers selectByUserName(@Param("UserName")String UserName);

    SystemUsers selectByEmail(@Param("Email")String Email);
}