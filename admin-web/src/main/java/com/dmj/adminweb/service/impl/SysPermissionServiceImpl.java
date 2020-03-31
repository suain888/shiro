package com.dmj.adminweb.service.impl;

import com.dmj.admincommon.common.Result;
import com.dmj.admincommon.pojo.dto.SysPermissionDTO;
import com.dmj.admincommon.pojo.vo.SysPermissionVO;
import com.dmj.admincommon.util.DataTransferUtils;
import com.dmj.adminweb.mapper.SysPermissionMapper;
import com.dmj.adminweb.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author Suian
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermissionDTO> implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;


    @Override
    public Result<SysPermissionVO> findPermissionList() {
        List<SysPermissionVO> sysPermissionVOS=sysPermissionMapper.findPermissionList();
        List<SysPermissionVO> result= DataTransferUtils.list2Tree(sysPermissionVOS,"id","parentId","children");
        return Result.success(result);
    }
}
