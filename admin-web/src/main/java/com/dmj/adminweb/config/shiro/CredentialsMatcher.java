package com.dmj.adminweb.config.shiro;

import com.dmj.admincommon.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 密码校验器
 *
 * @author Suian*/
@Slf4j
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        // 获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        String inPassword = new String(utoken.getPassword());
        // 获得数据库中的密码
        String dbPassword = (String) info.getCredentials();
        //md5加密的密码
        String encryPassword= null;
        try {
            encryPassword = MD5Utils.MD5SendParame(inPassword);
        } catch (Exception e) {
            log.error("md5加密失败",e);
        }
        // 进行密码的比对
        return this.equals(encryPassword, dbPassword);
    }
}

