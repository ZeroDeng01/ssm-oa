package com.zerodeng.shiro;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.zerodeng.bean.system.SystemUsers;
import com.zerodeng.controller.system.Users.Users;
import com.zerodeng.service.system.Users.SystemUsersService;
import com.zerodeng.utils.Encrypt;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @ClassName:AuthRealm
 * @Description:TODO 权限认证
 * @Author:ZeroDeng
 * @Date:2018-12-10 21:52
 * @Version:1.0
 **/
public class AuthRealm  extends AuthorizingRealm {
    private static Logger logger = Logger.getLogger(AuthRealm.class);

    @Resource
    private SystemUsersService systemUsersService;

    /**
     * 用于权限的认证。
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SystemUsers user = (SystemUsers) principalCollection.getPrimaryPrincipal();
        String username = user.getUser();
        logger.info("用户id"+user.getId());
        logger.info("用户名"+user.getUser());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;

        //Set<String> roleName = authLocalService.findRoles(username) ;//角色
        //Set<String> permissions = authLocalService.findPermissions(username) ;//权限（模块）
        //info.setRoles(roleName);
        //info.setStringPermissions(permissions);
        return null;//info;
    }

    /**
     * 首先执行这个登录验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToke = (UsernamePasswordToken)token;
        String username = usernamePasswordToke.getUsername();
        String password = String.valueOf(usernamePasswordToke.getPassword());
        //password = Encrypt.UserPwdSHA256(password,username);
        SystemUsers user = systemUsersService.selectByUserName(username);
        if( user == null ){
            throw new UnknownAccountException("用户不存在");
        }
        if( !user.getPwd().equals(password)){
            throw new IncorrectCredentialsException("用户名或者密码错误");
        }
//		if(Boolean.TRUE.equals( user.getLocked())){
//			  throw new LockedAccountException(); //帐号锁定
//		}
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,password,this.getName());
        return authenticationInfo;
    }



    public void setCredentialMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("SHA-256");//sha256算法加密
        credentialsMatcher.setHashIterations(1024);//1024次循环加密
        setCredentialsMatcher(credentialsMatcher);
    }
}
