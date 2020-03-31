package com.dmj.adminweb.controller;


import com.dmj.admincommon.common.Result;
import com.dmj.admincommon.pojo.query.sys.SysUserQuery;
import com.dmj.admincommon.pojo.query.sys.UserQuery;
import com.dmj.admincommon.pojo.vo.SysRoleVO;
import com.dmj.admincommon.pojo.vo.SysUserVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import com.dmj.adminweb.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Suian
 */
@RestController
@Api(tags = "用户接口")
public class SysUserController {

    @Resource
    private SysUserService service;

//    @RequiresPermissions("sys:user:create")
    @PostMapping("/sys-user")
    @ApiOperation("新增用户")
    public Result<SysUserQuery> save(@RequestBody SysUserQuery entity) {
        return service.insertSysUser(entity);
    }

//    @RequiresPermissions("sys:user:update")
    @PutMapping("/sys-user")
    @ApiOperation("修改用户信息")
    public Result<SysUserQuery> update(@RequestBody SysUserQuery entity) {
        return service.updateSysUser(entity);
    }

//    @RequiresPermissions("sys:user:delete")
    @DeleteMapping("/sys-user")
    @ApiOperation("删除用户信息")
    public Result delete(@RequestBody List<String> ids) {
        for (String id : ids) {
            service.removeById(id);
        }
        return Result.success();
    }

//    @RequiresPermissions("sys:user:view")
    @GetMapping("/user-page")
    @ApiOperation("分页查询用户信息")
    public Result<PageInfo<SysUserVO>> findUserPage(@ModelAttribute UserQuery userQuery) {
        return service.findUserPage(userQuery);
    }

//    @RequiresPermissions("sys:user:view")
    @ApiOperation("根据用户ID查询角色及权限列表信息")
    @GetMapping("role-permission")
    public Result<List<SysRoleVO>> findRolePermission(@RequestParam String userId) {
        return service.findRoleByUserId(userId);
    }
}

