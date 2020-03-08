package com.dmj.adminweb.service;

import com.dmj.admincommon.common.Result;
import com.dmj.admincommon.pojo.dto.SysUserDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.admincommon.pojo.query.sys.SysUserQuery;
import com.dmj.admincommon.pojo.query.sys.UserQuery;
import com.dmj.admincommon.pojo.vo.SysRoleVO;
import com.dmj.admincommon.pojo.vo.SysUserVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dongzhang
 * @since 2020-01-26
 */
public interface SysUserService extends IService<SysUserDTO> {


    Result<SysUserQuery> insertSysUser(SysUserQuery sysUserDTO);

    Result<SysUserQuery> updateSysUser(SysUserQuery sysUserDTO);

    Result<PageInfo<SysUserVO>> findUserPage(UserQuery userQuery);

    Result<List<SysRoleVO>> findRoleByUserId(String userId);
}
