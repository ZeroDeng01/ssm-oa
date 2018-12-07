package com.zerodeng.service.system.Users.impl;

import com.zerodeng.bean.system.SystemUsers;
import com.zerodeng.dao.system.SystemUsersMapper;
import com.zerodeng.service.system.Users.SystemUsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("UserService")
public class SystemUsersServiceImpl implements SystemUsersService {
    @Resource
    private SystemUsersMapper systemUsersMapper;
    /**
    * @Author ZeroDeng
    * @Description TODO 用户名密码校验
    * @Date 9:24 2018-12-05
    * @Param [UserName, Password]
    * @return com.zerodeng.bean.User
    * @Version 1.0
    **/
    public SystemUsers login(String UserName, String Password){
        SystemUsers user = systemUsersMapper.login(UserName,Password);
        return user;
    }
    /**
    * @Author ZeroDeng
    * @Description TODO 根据用户名查询用户
    * @Date 11:20 2018-12-05
    * @Param [UserName]
    * @return com.zerodeng.bean.User
    * @Version 1.0
    **/
    public SystemUsers selectByUserName(String UserName){
        SystemUsers user = systemUsersMapper.selectByUserName(UserName);
        return user;
    }
    /**
    * @Author ZeroDeng
    * @Description TODO 根据邮箱查询用户
    * @Date 11:20 2018-12-05
    * @Param [Email]
    * @return com.zerodeng.bean.User
    * @Version 1.0
    **/
    public SystemUsers selectByEmail(String Email){
        SystemUsers user = systemUsersMapper.selectByEmail(Email);
        return user;
    }
    /**
    * @Author ZeroDeng
    * @Description TODO 添加用户
    * @Date 13:42 2018-12-05
    * @Param [user]
    * @return int
    * @Version 1.0
    **/
    public int insertUser(SystemUsers user){
        int num = systemUsersMapper.insert(user);
        return num;
    }
}
