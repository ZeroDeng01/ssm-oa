package com.zerodeng.controller;

import com.zerodeng.bean.system.SystemUsers;
import com.zerodeng.service.system.Users.SystemUsersService;
import com.zerodeng.utils.Encrypt;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName:SignUp
 * @Description:TODO
 * @Author:ZeroDeng
 * @Date:2018-12-05 9:38
 * @Version:1.0
 **/
@Controller
@RequestMapping("/signup")
public class SignUp {
    private static Logger logger = Logger.getLogger(SignUp.class);
    @Resource
    private SystemUsersService systemUsersService;
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String SignUp(Model model){
        return "WEB-INF/jsp/sign-up";
    }

    /**
    * @Author ZeroDeng
    * @Description TODO 检测用户名是否存在
    * @Date 11:26 2018-12-05
    * @Param [UserName]
    * @return java.lang.String
    * @Version 1.0
    **/
    @RequestMapping(value = "signup",method = RequestMethod.POST)
    public ModelAndView signupDo(@RequestParam("name")String Name, @RequestParam("username")String UserName, @RequestParam("email")String Email, @RequestParam("password")String Password,
                                 HttpServletRequest request, HttpServletResponse response){

        SystemUsers user = null;
        user = systemUsersService.selectByUserName(UserName);
        if(user!=null){
            ModelAndView modelAndView = new ModelAndView("WEB-INF/jsp/sign-up");
            modelAndView.addObject("name",Name);
            modelAndView.addObject("username",UserName);
            modelAndView.addObject("email",Email);
            modelAndView.addObject("password",Password);
            modelAndView.addObject("Msg","该用户名已被注册");
            return modelAndView;
        }
        user = systemUsersService.selectByEmail(Email);
        if(user!=null){
            ModelAndView modelAndView = new ModelAndView("WEB-INF/jsp/sign-up");
            modelAndView.addObject("name",Name);
            modelAndView.addObject("username",UserName);
            modelAndView.addObject("email",Email);
            modelAndView.addObject("password",Password);
            modelAndView.addObject("Msg","该邮箱已被注册");
            return modelAndView;
        }
        user = new SystemUsers();
        user.setName(Name);
        user.setUser(UserName);
        user.setEmail(Email);
        Password = Encrypt.UserPwdSHA256(Password,UserName);
        user.setPwd(Password);
        user.setPhone("");
        int status = systemUsersService.insertUser(user);
        if(status>0){
            ModelAndView modelAndView = new ModelAndView("WEB-INF/jsp/login");
            modelAndView.addObject("Msg","注册成功，请登录");
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("WEB-INF/jsp/sign-up");
            modelAndView.addObject("name",Name);
            modelAndView.addObject("username",UserName);
            modelAndView.addObject("email",Email);
            modelAndView.addObject("password",Password);
            modelAndView.addObject("Msg","注册失败");
            return modelAndView;
        }


    }

}
