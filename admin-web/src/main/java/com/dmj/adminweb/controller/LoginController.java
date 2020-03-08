package com.dmj.adminweb.controller;

import com.dmj.admincommon.common.Result;
import com.dmj.admincommon.pojo.query.LoginQuery;
import com.dmj.adminweb.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "登录接口")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation("登录")
    @PostMapping(value = "login")
    public Result login(@RequestBody LoginQuery loginQuery) {
        return loginService.login(loginQuery.getEmail(),loginQuery.getPassword());
    }


    @ApiOperation("退出")
    @GetMapping("exit")
    public Result exit() {
        return loginService.exit();
    }
}
