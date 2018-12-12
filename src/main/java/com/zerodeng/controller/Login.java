package com.zerodeng.controller;

import com.zerodeng.bean.system.SystemUsers;
import com.zerodeng.service.system.Users.SystemUsersService;
import com.zerodeng.utils.Encrypt;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String Login(HttpServletRequest request, HttpServletResponse response, Model model){

        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length > 0){
            for(Cookie c:cookies){
                if(c.getName().equals("user")){
                    model.addAttribute("username",c.getValue());
                }
                if(c.getName().equals("pwd")){
                    model.addAttribute("password",c.getValue());
                }
                if(c.getName().equals("r")){
                    model.addAttribute("r",c.getValue());
                }
            }
        }
        return "WEB-INF/jsp/login";
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
    public String UserLogin(HttpServletRequest request, HttpServletResponse response,Model model){
        String UserName =request.getParameter("UserName");
        String Password = request.getParameter("Password");
        String ePassword = Encrypt.UserPwdSHA256(Password,UserName);
        String remember_me = request.getParameter("remember-me");
        if(remember_me==null){
            remember_me = "";
        }
        //String encPassword = Encrypt.UserPwdSHA256(Password,UserName);
        //SystemUsers user = systemUsersService.login(UserName,encPassword);


        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(UserName, ePassword);
        try {
            //执行认证操作.
            subject.login(token);
        }catch (AuthenticationException ae) {
            logger.info("登陆失败: " + ae.getMessage());
            model.addAttribute("Msg",ae.getMessage());
            return "WEB-INF/jsp/login";
        }




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
        return "redirect:index.do";
        /*
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
        */
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
    public String UserLoginOut(HttpServletRequest request, HttpServletResponse response,SystemUsers user,Model model){
        model.addAttribute("Msg","退出成功");
        /*
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1);
        session.setAttribute("user",null);
        */
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length > 0){
            for(Cookie c:cookies){
                if(c.getName().equals("user")){
                    model.addAttribute("username",c.getValue());
                }
                if(c.getName().equals("pwd")){
                    model.addAttribute("password",c.getValue());
                }
                if(c.getName().equals("r")){
                    model.addAttribute("r",c.getValue());
                }
            }
        }
        return "WEB-INF/jsp/login";
    }
}

