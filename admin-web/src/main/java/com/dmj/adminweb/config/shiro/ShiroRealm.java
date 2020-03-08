package com.dmj.adminweb.config.shiro;


import com.dmj.admincommon.constant.CommonConstant;
import com.dmj.admincommon.pojo.vo.SysPermissionVO;
import com.dmj.admincommon.pojo.vo.SysRoleVO;
import com.dmj.admincommon.pojo.vo.SysUserVO;
import com.dmj.adminweb.mapper.SysPermissionMapper;
import com.dmj.adminweb.mapper.SysRoleMapper;
import com.dmj.adminweb.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

/**
 * 自定义realm
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;


    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        log.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        String name = authcToken.getPrincipal().toString();
        // 从数据库获取对应用户名密码的用户
        SysUserVO sysUserVO = userMapper.findUserByUserName(name);
        if (sysUserVO != null) {
             //用户为禁用状态
            if (sysUserVO.getDeleteFlag()) {
                throw new DisabledAccountException();
            }
            //用户为锁定状态，不允许登录
            if (sysUserVO.getLocked()) {
                throw new DisabledAccountException();
            }
             //把当前用户存到 Session 中
            SecurityUtils.getSubject().getSession().setAttribute(CommonConstant.session, sysUserVO);
            log.info("---------------- Shiro 凭证认证成功 ----------------------");
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    sysUserVO, //用户
                    sysUserVO.getUserPassword(), //密码
                    getName()  //realm name
            );
            return authenticationInfo;
        }
        throw new UnknownAccountException();
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (principal instanceof SysUserVO) {
            SysUserVO sysUserVO = (SysUserVO) principal;
            if(sysUserVO != null){
                List<SysRoleVO> roleList = roleMapper.findRoleListByUserId(sysUserVO.getId());
                if(!Objects.isNull(roleList) || roleList.size() > 0) {
                    for(SysRoleVO role : roleList) {
                        info.addRole(role.getRoleName());
                        List<SysPermissionVO> sysPermissionVOS=sysPermissionMapper.findPermissionByRoleId(role.getId());
                        if(!Objects.isNull(sysPermissionVOS) || sysPermissionVOS.size()>0) {
                            for (SysPermissionVO menu : role.getPermissionVOList()) {
                                if(StringUtils.isNotBlank(menu.getPermissionStr())) {
                                    info.addStringPermission(menu.getPermissionStr());
                                }
                            }
                        }
                    }
                }
            }
        }
        log.info("---------------- 获取到以下权限 ----------------");
        log.info(info.getStringPermissions().toString());
        log.info("---------------- Shiro 权限获取成功 ----------------------");
        return info;
    }
}

