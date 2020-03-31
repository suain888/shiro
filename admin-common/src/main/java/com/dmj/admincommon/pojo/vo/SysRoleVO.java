package com.dmj.admincommon.pojo.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @author Suian
 */
@Data
public class SysRoleVO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    @TableField("role_desc")
    private String roleDesc;

    @ApiModelProperty(value = "是否系统默认")
    @TableField("sys_default")
    private Boolean sysDefault;

    @TableField("compay_id")
    private String compayId;

    @ApiModelProperty(value = "创建者")
    @TableField("creater")
    private String creater;

    @TableField("create_date")
    private Date createDate;

    @TableField("updater")
    private String updater;

    @TableField("update_date")
    private Date updateDate;

    @ApiModelProperty(value = "0:可用 1:不可用")
    @TableField("delete_flag")
    @TableLogic
    private Boolean deleteFlag;

    private List<SysPermissionVO> permissionVOList;
}
