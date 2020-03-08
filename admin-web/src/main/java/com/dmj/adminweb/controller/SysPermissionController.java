package com.dmj.adminweb.controller;


import com.dmj.admincommon.common.Result;
import com.dmj.admincommon.pojo.dto.SysPermissionDTO;
import com.dmj.admincommon.pojo.vo.SysPermissionVO;
import com.dmj.adminweb.config.shiro.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.dmj.adminweb.service.SysPermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "权限接口")
public class SysPermissionController {

    @Resource
    private SysPermissionService service;

//    @RequiresPermissions("sys:permission:create")
    @PostMapping("/sys-permission")
    @ApiOperation("新增权限信息")
    public Result<SysPermissionDTO> save(@RequestBody SysPermissionDTO entity) {
        entity.setCreateDate(new Date());
//        entity.setCreater(ShiroUtils.getLoginName());
        service.saveOrUpdate(entity);
        return Result.success(entity);
    }

//    @RequiresPermissions("sys:permission:update")
    @PutMapping("/sys-permission")
    @ApiOperation("修改权限信息")
    public Result<SysPermissionDTO> update(@RequestBody SysPermissionDTO entity) {
        entity.setUpdateDate(new Date());
//        entity.setUpdater(ShiroUtils.getLoginName());
        service.saveOrUpdate(entity);
        return Result.success(entity);
    }

//    @RequiresPermissions("sys:permission:delete")
    @DeleteMapping("/sys-permission")
    @ApiOperation("删除权限信息")
    public Result delete(@RequestBody List<String> ids) {
        for (String id:ids) {
            service.removeById(id);
        }
        return Result.success();
    }

//    @RequiresPermissions("sys:permission:view")
    @GetMapping("/permission-list")
    @ApiOperation("查询权限列表")
    public Result<SysPermissionVO> findPermissionList() {
        return service.findPermissionList();
    }

}

