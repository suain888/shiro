package com.dmj.adminweb.mapper;

import com.dmj.admincommon.pojo.dto.SysUserDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmj.admincommon.pojo.query.sys.UserQuery;
import com.dmj.admincommon.pojo.vo.SysUserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUserDTO> {

    SysUserVO findUserByUserName(@Param("email") String email);

    /**
     * 根据角色ID查询用户姓名
     * @param roleId
     * @return
     */
    List<String> findUserName(@Param("roleId") String roleId);

    List<SysUserVO> findUserList(UserQuery userQuery);

    /**
     * 根绝角色ID查询用户列表
     * @param roleId
     * @return
     */
    List<SysUserVO> findUserListByRoleId(@Param("roleId") String roleId);
}
