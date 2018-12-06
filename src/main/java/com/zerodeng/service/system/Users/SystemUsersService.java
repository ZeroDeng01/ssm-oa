package com.zerodeng.service.system.Users;

import com.zerodeng.bean.system.SystemUsers;

public interface SystemUsersService {
    /**
    * @Author ZeroDeng
    * @Description TODO 用户名密码校验
    * @Date 9:26 2018-12-05
    * @Param [UserName, Password]
    * @return com.zerodeng.bean.User
    * @Version 1.0
    **/
    public SystemUsers login(String UserName, String Password);
    /**
     * @Author ZeroDeng
     * @Description TODO 根据用户名查询用户
     * @Date 11:20 2018-12-05
     * @Param [UserName]
     * @return com.zerodeng.bean.User
     * @Version 1.0
     **/
    public SystemUsers selectByUserName(String UserName);
    /**
     * @Author ZeroDeng
     * @Description TODO 根据邮箱查询用户
     * @Date 11:20 2018-12-05
     * @Param [Email]
     * @return com.zerodeng.bean.User
     * @Version 1.0
     **/
    public SystemUsers selectByEmail(String Email);
    /**
    * @Author ZeroDeng
    * @Description TODO 添加用户
    * @Date 13:42 2018-12-05
    * @Param [user]
    * @return int
    * @Version 1.0
    **/
    public int insertUser(SystemUsers user);
}
