package com.dmj.adminweb.service;

import com.dmj.admincommon.common.Result;
import com.dmj.admincommon.pojo.dto.SysRoleDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.admincommon.pojo.query.sys.RoleQuery;
import com.dmj.admincommon.pojo.query.sys.SysRoleQuery;
import com.dmj.admincommon.pojo.vo.SysRoleVO;
import com.github.pagehelper.PageInfo;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
/**
 * @author Suian
 */
public interface SysRoleService extends IService<SysRoleDTO> {

    Result<SysRoleQuery> insertRole(SysRoleQuery sysRoleQuery);

    Result<SysRoleQuery> updateRole(SysRoleQuery sysRoleQuery);

    Result delete(List<String> ids);

    Result<SysRoleVO> findRoleList();

    Result<PageInfo<SysRoleVO>> findRolePage(RoleQuery roleQuery);
}
