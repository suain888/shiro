package com.dmj.adminweb.mapper;

import com.dmj.admincommon.pojo.dto.SysPermissionDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.admincommon.pojo.vo.SysPermissionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionMapper extends BaseMapper<SysPermissionDTO> {

     List<SysPermissionVO> findPermissionList();

     List<SysPermissionVO> findPermissionByRoleId(@Param("roleId") String roleId);
}
