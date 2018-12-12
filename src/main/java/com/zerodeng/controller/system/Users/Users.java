package com.zerodeng.controller.system.Users;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName:Users
 * @Description:TODO 用户管理
 * @Author:ZeroDeng
 * @Date:2018-12-08 22:44
 * @Version:1.0
 **/
@Controller
@RequestMapping("/System/Users")
public class Users {
    private static Logger logger = Logger.getLogger(Users.class);

    @RequestMapping(value = "UsersList")
    public String UsersList(HttpServletRequest request, HttpServletResponse response,Model model){
        return "WEB-INF/jsp/page/system/users/usersList";
    }
}
