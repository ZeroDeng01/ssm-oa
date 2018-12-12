package com.zerodeng.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.log4j.Logger;

/**
 * @ClassName:URLAuthenticationFilter
 * @Description:TODO 自定义过滤器
 * @Author:ZeroDeng
 * @Date:2018-12-11 9:31
 * @Version:1.0
 **/
public class URLAuthenticationFilter  {
    private static Logger logger = Logger.getLogger(URLAuthenticationFilter.class);
}
