package com.dmj.adminweb.service;

import com.dmj.admincommon.common.Result;
import com.dmj.admincommon.pojo.dto.SysPermissionDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dmj.admincommon.pojo.vo.SysPermissionVO;

public interface SysPermissionService extends IService<SysPermissionDTO> {

    Result<SysPermissionVO> findPermissionList();
}
