package com.dmj.admincommon.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author dongzhang
 * @since 2020-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_permission")
@ApiModel(value="SysRolePermission对象", description="")
public class SysRolePermissionDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("role_id")
    private String roleId;

    @TableField("permission_id")
    private String permissionId;

    @TableField("creater")
    private String creater;

    @TableField("create_date")
    private Date createDate;

    @TableField("updater")
    private String updater;

    @TableField("update_date")
    private Date updateDate;

    @TableField("delete_flag")
    @TableLogic
    private Boolean deleteFlag;


        }
