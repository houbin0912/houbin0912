package com.baixin.ees.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.baixin.ees.util.DesUtil;

/**
 * 自定义认证类
 * 
 * @Description
 * @author: xjj
 * @CreateDate:2016年1月23日
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        return equals(DesUtil.encryptStr(new String(token.getPassword())), getCredentials(info));

    }
}
