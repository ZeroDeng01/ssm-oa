package com.zerodeng.controller;

import com.zerodeng.bean.system.SystemUsers;
import com.zerodeng.service.system.Users.SystemUsersService;
import com.zerodeng.utils.Encrypt;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName:Login
 * @Description:TODO 登陆登出
 * @Author:ZeroDeng
 * @Date:2018-12-04 17:08
 * @Version:1.0
 **/
@Controller
public class Login {
    private static Logger logger = Logger.getLogger(Login.class);
    @Resource
    private SystemUsersService systemUsersService;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public ModelAndView Login(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("WEB-INF/jsp/login");

        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length > 0){
            for(Cookie c:cookies){
                if(c.getName().equals("user")){
                    modelAndView.addObject("username",c.getValue());
                }
                if(c.getName().equals("pwd")){
                    modelAndView.addObject("password",c.getValue());
                }
                if(c.getName().equals("r")){
                    modelAndView.addObject("r",c.getValue());
                }
            }
        }
        return modelAndView;
    }
    /**
     * @Author ZeroDeng
     * @Description TODO 登陆
     * @Date 15:01 2018-12-04
     * @Param [request, response]
     * @return org.springframework.web.servlet.ModelAndView
     * @Version 1.0
     **/
    @RequestMapping(value = "/UserLogin",method = RequestMethod.POST)
    public ModelAndView UserLogin(HttpServletRequest request, HttpServletResponse response){
        String UserName =request.getParameter("UserName");
        String Password = request.getParameter("Password");
        String remember_me = request.getParameter("remember-me");
        if(remember_me==null){
            remember_me = "";
        }
        ModelAndView modelAndView = null;
        String encPassword = Encrypt.UserPwdSHA256(Password);
        SystemUsers user = systemUsersService.login(UserName,encPassword);
        if(user!=null){
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(-1);
            session.setAttribute("user",user);
            modelAndView =new ModelAndView("WEB-INF/jsp/index");
            modelAndView.addObject("UserName",user.getName());
            if(remember_me.equals("on")){//记住密码设置cookie
                Cookie usercookie = new Cookie("user",UserName);
                usercookie.setMaxAge(3600 * 24*30);
                usercookie.setPath("/");
                response.addCookie(usercookie);
                Cookie pwdcookie = new Cookie("pwd",Password);
                pwdcookie.setMaxAge(3600 * 24*30);
                pwdcookie.setPath("/");
                response.addCookie(pwdcookie);
                Cookie rcookie = new Cookie("r","1");
                rcookie.setMaxAge(3600 * 24*30);
                rcookie.setPath("/");
                response.addCookie(rcookie);
            }else{
                Cookie usercookie = new Cookie("user","");
                usercookie.setMaxAge(1);
                usercookie.setPath("/");
                response.addCookie(usercookie);
                Cookie pwdcookie = new Cookie("pwd","");
                pwdcookie.setMaxAge(1);
                pwdcookie.setPath("/");
                response.addCookie(pwdcookie);
                Cookie rcookie = new Cookie("r","0");
                rcookie.setMaxAge(1);
                rcookie.setPath("/");
                response.addCookie(rcookie);
            }
        }else{
            modelAndView =new ModelAndView("WEB-INF/jsp/login");
            modelAndView.addObject("Msg","用户名或者密码错误");
        }
        return modelAndView;
    }
    /**
    * @Author ZeroDeng
    * @Description TODO 退出
    * @Date 20:01 2018-12-04
    * @Param [request, response, user]
    * @return org.springframework.web.servlet.ModelAndView
    * @Version 1.0
    **/
    @RequestMapping(value = "/UserLoginOut",method = RequestMethod.GET)
    public ModelAndView UserLoginOut(HttpServletRequest request, HttpServletResponse response,SystemUsers user){
        ModelAndView modelAndView = new ModelAndView("WEB-INF/jsp/login");
        modelAndView.addObject("Msg","退出成功");
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1);
        session.setAttribute("user",null);
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length > 0){
            for(Cookie c:cookies){
                if(c.getName().equals("user")){
                    modelAndView.addObject("username",c.getValue());
                }
                if(c.getName().equals("pwd")){
                    modelAndView.addObject("password",c.getValue());
                }
                if(c.getName().equals("r")){
                    modelAndView.addObject("r",c.getValue());
                }
            }
        }
        return modelAndView;
    }
}

