package com.dmj.adminweb.service;

import com.dmj.admincommon.common.Result;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    /**
     * 登录
     * @param email
     * @param password
     * @return
     */
    Result login(String email, String password);

    /**
     * 退出
     * @return
     */
    Result exit();
}
