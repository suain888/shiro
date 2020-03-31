package com.dmj.adminweb.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmj.admincommon.common.Result;
import com.dmj.admincommon.common.ResultStatusCode;
import com.dmj.admincommon.emus.OnlineStatus;
import com.dmj.admincommon.pojo.SysUserOnline;
import com.dmj.admincommon.pojo.dto.SysRoleDTO;
import com.dmj.admincommon.pojo.dto.SysRolePermissionDTO;
import com.dmj.admincommon.pojo.query.sys.RoleQuery;
import com.dmj.admincommon.pojo.query.sys.SysRoleQuery;
import com.dmj.admincommon.pojo.vo.SysPermissionVO;
import com.dmj.admincommon.pojo.vo.SysRoleVO;
import com.dmj.admincommon.pojo.vo.SysUserVO;
import com.dmj.admincommon.util.DataTransferUtils;
import com.dmj.admincommon.util.JsonUtils;
import com.dmj.adminweb.config.shiro.ShiroUtils;
import com.dmj.adminweb.config.shiro.session.OnlineSession;
import com.dmj.adminweb.config.shiro.session.OnlineSessionDAO;
import com.dmj.adminweb.mapper.*;
import com.dmj.adminweb.service.ISysUserOnlineService;
import com.dmj.adminweb.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
/**
 * @author Suian
 */
@Service
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleDTO> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

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

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    @Transactional
    public Result<SysRoleQuery> insertRole(SysRoleQuery sysRoleQuery) {
        log.info("新增的角色信息为:{}", JsonUtils.getBeanToJson(sysRoleQuery));
        SysRoleDTO sysRoleDTO=dozerBeanMapper.map(sysRoleQuery,SysRoleDTO.class);
        sysRoleDTO.setCreateDate(new Date());
//        sysRoleDTO.setCreater(ShiroUtils.getLoginName());
        sysRoleMapper.insert(sysRoleDTO);
        if(!Objects.isNull(sysRoleQuery.getPermissionDTOList()) || sysRoleQuery.getPermissionDTOList().size()>0) {
            for(String permissionId:sysRoleQuery.getPermissionDTOList()) {
                SysRolePermissionDTO sysRolePermissionDTO=new SysRolePermissionDTO();
                sysRolePermissionDTO.setRoleId(sysRoleDTO.getId());
                sysRolePermissionDTO.setPermissionId(permissionId);
                sysRolePermissionMapper.insert(sysRolePermissionDTO);
            }
        }
        return Result.success(sysRoleQuery);
    }

    @Override
    @Transactional
    public Result<SysRoleQuery> updateRole(SysRoleQuery sysRoleQuery) {
        log.info("修改的角色信息为:{}", JsonUtils.getBeanToJson(sysRoleQuery));
        SysRoleDTO sysRoleDTO=dozerBeanMapper.map(sysRoleQuery,SysRoleDTO.class);
//        sysRoleDTO.setUpdater(ShiroUtils.getLoginName());
        sysRoleDTO.setUpdateDate(new Date());
        sysRoleMapper.updateById(sysRoleDTO);
        //删除相关权限
        sysRolePermissionMapper.delete(Wrappers.<SysRolePermissionDTO>lambdaUpdate().eq(SysRolePermissionDTO::getRoleId,sysRoleDTO.getId()));
        if(!Objects.isNull(sysRoleQuery.getPermissionDTOList()) || sysRoleQuery.getPermissionDTOList().size()>0) {
            for(String permissionId:sysRoleQuery.getPermissionDTOList()) {
                SysRolePermissionDTO sysRolePermissionDTO=new SysRolePermissionDTO();
                sysRolePermissionDTO.setRoleId(sysRoleDTO.getId());
                sysRolePermissionDTO.setPermissionId(permissionId);
                sysRolePermissionMapper.insert(sysRolePermissionDTO);
            }
        }
        List<SysUserVO> sysUserVOS=sysUserMapper.findUserListByRoleId(sysRoleDTO.getId());
        for(SysUserVO sysUserVO:sysUserVOS) {
            SysUserOnline sysUserOnline = sysUserOnlineMapper.selectOnlineByEmail(sysUserVO.getEmail());
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
        }
        return Result.success(sysRoleQuery);
    }

    @Override
    @Transactional
    public Result delete(List<String> ids) {
        for(String id:ids) {
            SysRoleDTO sysRoleDTO=sysRoleMapper.selectById(id);
            //判断是否为系统默认角色
            if(sysRoleDTO.getSysDefault()) {
                return Result.fail(ResultStatusCode.DELETE_ERROR);
            }
            //判断该角色是否与管理员绑定
            List<String> userNames=sysUserMapper.findUserName(id);
            if(userNames.contains("管理员")) {
                return Result.fail(ResultStatusCode.DELETE_ERROR_INFO);
            }
            sysRoleMapper.deleteById(id);
        }
        return Result.success();
    }

    @Override
    public Result<SysRoleVO> findRoleList() {
        List<SysRoleVO> sysRoleVOS=sysRoleMapper.findRoleList(null);
        //角色所属的权限
        for(SysRoleVO sysRoleVO:sysRoleVOS) {
            List<SysPermissionVO> sysPermissionVOS=sysPermissionMapper.findPermissionByRoleId(sysRoleVO.getId());
            //转换为树形结构
            List<SysPermissionVO> sysPermissionTree=DataTransferUtils.list2Tree(sysPermissionVOS,
                    "id","parentId","children");
            sysRoleVO.setPermissionVOList(sysPermissionTree);
        }
        return Result.success(sysRoleVOS);
    }

    @Override
    public Result<PageInfo<SysRoleVO>> findRolePage(RoleQuery roleQuery) {
        PageHelper.startPage(roleQuery.getPageNum(),roleQuery.getPageSize());
        List<SysRoleVO> sysRoleVOS=sysRoleMapper.findRoleList(roleQuery.getKeyWord());
        //角色所属的权限
        for(SysRoleVO sysRoleVO:sysRoleVOS) {
            List<SysPermissionVO> sysPermissionVOS=sysPermissionMapper.findPermissionByRoleId(sysRoleVO.getId());
            //转换为树形结构
            List<SysPermissionVO> sysPermissionTree=DataTransferUtils.list2Tree(sysPermissionVOS,
                    "id","parentId","children");
            sysRoleVO.setPermissionVOList(sysPermissionTree);
        }
        PageInfo pageInfo=new PageInfo(sysRoleVOS);
        return Result.success(pageInfo);
    }
}
