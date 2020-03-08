package com.dmj.adminweb.service.impl;

import com.dmj.admincommon.common.Result;
import com.dmj.admincommon.common.ResultStatusCode;
import com.dmj.admincommon.pojo.vo.SysUserVO;
import com.dmj.adminweb.mapper.SysUserMapper;
import com.dmj.adminweb.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService  {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public Result login(String email, String password) {
        try {
            //登录不在该处处理，交由shiro处理
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(email, password);
            subject.login(token);
            if (subject.isAuthenticated()) {
                SysUserVO sysUserVO=sysUserMapper.findUserByUserName(email);
                return Result.success(sysUserVO);
            } else {
                return Result.fail(ResultStatusCode.USER_AUTHER_ERROR);
            }
        } catch (IncorrectCredentialsException e){
            log.error("密码不正确",e);
            return Result.fail(ResultStatusCode.NOT_EXIST_USER);
        } catch (LockedAccountException e){
            log.error("用户已经锁定",e);
            return Result.fail(ResultStatusCode.USER_FROZEN);
        } catch (UnknownAccountException e) {
            log.error("用户存在",e);
            return Result.fail(ResultStatusCode.NOT_EXIST_USER);
        }  catch (Exception e) {
            log.error("系统异常", e);
            return Result.fail(ResultStatusCode.SYSTEM_ERR);
        }
    }

    @Override
    public Result exit() {
        SecurityUtils.getSubject().logout();
        return Result.success();
    }
}
