package com.dmj.adminweb.controller;


import com.dmj.admincommon.common.Result;
import com.dmj.admincommon.pojo.dto.SysRoleDTO;
import com.dmj.admincommon.pojo.query.sys.RoleQuery;
import com.dmj.admincommon.pojo.query.sys.SysRoleQuery;
import com.dmj.admincommon.pojo.vo.SysRoleVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dmj.adminweb.service.SysRoleService;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "角色接口")
public class SysRoleController {

    @Resource
    private SysRoleService service;

//    @RequiresPermissions("sys:role:create")
    @PostMapping("/sys-role")
    @ApiOperation("新增角色信息")
    public Result<SysRoleQuery> save(@RequestBody SysRoleQuery entity) {
        service.insertRole(entity);
        return Result.success(entity);
    }

//    @RequiresPermissions("sys:role:update")
    @PutMapping("/sys-role")
    @ApiOperation("修改角色信息")
    public Result<SysRoleQuery> update(@RequestBody SysRoleQuery entity) {
        service.updateRole(entity);
        return Result.success(entity);
    }


//    @RequiresPermissions("sys:role:delete")
    @DeleteMapping("/sys-role")
    @ApiOperation("删除角色信息")
    public Result delete(@RequestBody List<String> ids) {
        return service.delete(ids);
    }

//    @RequiresPermissions("sys:role:view")
    @GetMapping("role-list")
    @ApiOperation("查询角色列表")
    public Result<SysRoleVO> findRoleList() {
        return service.findRoleList();
    }

//    @RequiresPermissions("sys:role:view")
    @GetMapping("role-page")
    @ApiOperation("分页查询角色列表")
    public Result<PageInfo<SysRoleVO>> findRolePage(@ModelAttribute RoleQuery roleQuery) {
        return service.findRolePage(roleQuery);
    }

}

