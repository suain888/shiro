package com.dmj.adminweb.mapper;

import com.dmj.admincommon.pojo.dto.SysRoleDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.admincommon.pojo.vo.SysRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dongzhang
 * @since 2020-01-26
 */
public interface SysRoleMapper extends BaseMapper<SysRoleDTO> {

    List<SysRoleVO> findRoleListByUserId(@Param("userId") String userId);

    List<SysRoleVO> findRoleList(@Param("roleName") String roleName);
}
