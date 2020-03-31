package com.dmj.adminweb.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.admincommon.common.Result;
import com.dmj.admincommon.emus.OnlineStatus;
import com.dmj.admincommon.pojo.SysUserOnline;
import com.dmj.admincommon.pojo.dto.SysUserDTO;
import com.dmj.admincommon.pojo.dto.SysUserRoleDTO;
import com.dmj.admincommon.pojo.query.sys.SysUserQuery;
import com.dmj.admincommon.pojo.query.sys.UserQuery;
import com.dmj.admincommon.pojo.vo.SysPermissionVO;
import com.dmj.admincommon.pojo.vo.SysRoleVO;
import com.dmj.admincommon.pojo.vo.SysUserVO;
import com.dmj.admincommon.util.DataTransferUtils;
import com.dmj.admincommon.util.JsonUtils;
import com.dmj.admincommon.util.MD5Utils;
import com.dmj.adminweb.config.shiro.ShiroUtils;
import com.dmj.adminweb.config.shiro.session.OnlineSession;
import com.dmj.adminweb.config.shiro.session.OnlineSessionDAO;
import com.dmj.adminweb.mapper.*;
import com.dmj.adminweb.service.ISysUserOnlineService;
import com.dmj.adminweb.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Suian
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDTO> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysUserOnlineMapper sysUserOnlineMapper;

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    @Autowired
    private ISysUserOnlineService userOnlineService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;


    @Override
    @Transactional
    public Result<SysUserQuery> insertSysUser(SysUserQuery sysUserQuery) {
        log.info("新增用户信息为:{}", JsonUtils.getBeanToJson(sysUserQuery));
        SysUserVO sysUserVO=sysUserMapper.findUserByUserName(sysUserQuery.getEmail());
        if (!Objects.isNull(sysUserVO)) {
            return Result.fail("邮箱已存在，请重新输入");
        }
        SysUserDTO sysUserDTO=dozerBeanMapper.map(sysUserQuery,SysUserDTO.class);
        // 随机生成盐值
        String salt = RandomStringUtils.randomAlphanumeric(20);
        sysUserDTO.setSalt(salt);
        try {
            sysUserDTO.setUserPassword(MD5Utils.MD5SendParame(sysUserDTO.getUserPassword()));
        } catch (Exception e) {
            log.error("加密失败",e);
        }
        sysUserDTO.setCreateTime(new Date());
//        sysUserDTO.setCreator(ShiroUtils.getLoginName());
        sysUserMapper.insert(sysUserDTO);
        if(!Objects.isNull(sysUserQuery.getUserRoleDTOList()) || sysUserQuery.getUserRoleDTOList().size()>0) {
            for(String roleId:sysUserQuery.getUserRoleDTOList()) {
                SysUserRoleDTO sysUserRoleDTO=new SysUserRoleDTO();
                sysUserRoleDTO.setUserId(sysUserDTO.getId());
                sysUserRoleDTO.setRoleId(roleId);
                sysUserRoleMapper.insert(sysUserRoleDTO);
            }
        }
        return Result.success(sysUserQuery);
    }

    @Override
    @Transactional
    public Result<SysUserQuery> updateSysUser(SysUserQuery sysUserQuery) {
        log.info("修改的用户信息为:{}", JsonUtils.getBeanToJson(sysUserQuery));
        SysUserDTO sysUserDTO=dozerBeanMapper.map(sysUserQuery,SysUserDTO.class);
        sysUserDTO.setUpdateTime(new Date());
//        sysUserDTO.setUpdater(ShiroUtils.getLoginName());
        sysUserMapper.updateById(sysUserDTO);
        //清除角色数据
        sysUserRoleMapper.delete(Wrappers.<SysUserRoleDTO>lambdaUpdate().eq(SysUserRoleDTO::getUserId,sysUserDTO.getId()));
        if(!Objects.isNull(sysUserQuery.getUserRoleDTOList()) || sysUserQuery.getUserRoleDTOList().size()>0) {
            for(String roleId:sysUserQuery.getUserRoleDTOList()) {
                SysUserRoleDTO sysUserRoleDTO=new SysUserRoleDTO();
                sysUserRoleDTO.setUserId(sysUserDTO.getId());
                sysUserRoleDTO.setRoleId(roleId);
                sysUserRoleMapper.insert(sysUserRoleDTO);
            }
        }
        SysUserOnline sysUserOnline=sysUserOnlineMapper.selectOnlineByEmail(sysUserDTO.getEmail());
        //下线的用户为登录状态且不是当前登录用户
        if (!Objects.isNull(sysUserOnline) && !sysUserOnline.getSessionId().equals(ShiroUtils.getSessionId())) {
            OnlineSession onlineSession = (OnlineSession) onlineSessionDAO.readSession(sysUserOnline.getSessionId());
            if (!Objects.isNull(onlineSession)) {
                //修改为下线状态
                onlineSession.setStatus(OnlineStatus.off_line);
                onlineSessionDAO.update(onlineSession);
                sysUserOnline.setStatus(OnlineStatus.off_line);
                userOnlineService.saveOnline(sysUserOnline);
            }
         }
        return Result.success(sysUserQuery);
    }

    @Override
    public Result<PageInfo<SysUserVO>>  findUserPage(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPageNum(),userQuery.getPageSize());
        List<SysUserVO> sysUserVOS=sysUserMapper.findUserList(userQuery);
        PageInfo<SysUserVO> pageInfo=new PageInfo<>(sysUserVOS);
        return Result.success(pageInfo);
    }

    @Override
    public Result<List<SysRoleVO>> findRoleByUserId(String userId) {
        List<SysRoleVO> roleVOS=sysRoleMapper.findRoleListByUserId(userId);
        for (SysRoleVO sysRoleVO:roleVOS) {
            List<SysPermissionVO> sysPermissionVOS=sysPermissionMapper.findPermissionByRoleId(sysRoleVO.getId());
            List<SysPermissionVO> sysPermissionTree= DataTransferUtils.list2Tree(sysPermissionVOS,"id","parentId","children");
            sysRoleVO.setPermissionVOList(sysPermissionTree);
        }
        return Result.success(roleVOS);
    }
}
