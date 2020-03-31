package com.dmj.adminweb.controller;


import com.dmj.admincommon.common.Result;
import com.dmj.admincommon.common.ResultStatusCode;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 未授权活被踢出后的跳转方法
 *
 * @author Suian*/
@RestController
public class CommonController {

    /**
     * 未授权跳转方法
     * @return
     */
    @GetMapping("/common/unauth")
    public Result unauth() {
        SecurityUtils.getSubject().logout();
        return Result.fail(ResultStatusCode.NOT_PERMISSION);
    }

    /**
     * 被踢出后跳转方法
     * @return
     */
    @GetMapping("/common/kickout")
    public Result kickout() {
        return Result.fail(ResultStatusCode.INVALID_TOKEN);
    }

}
