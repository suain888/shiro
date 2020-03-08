package com.dmj.admincommon.pojo.dto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("sys_permission")
@ApiModel(value="SysPermission对象", description="")
public class SysPermissionDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "权限名称")
    @TableField("permission_name")
    private String permissionName;

    @ApiModelProperty(value = "权限类型('directory','button','menu')")
    @TableField("permission_type")
    private String permissionType;

    @ApiModelProperty(value = "权限路径")
    @TableField("permission_url")
    private String permissionUrl;

    @ApiModelProperty(value = "权限字符串(menu例子：role:*，button例子：role:create,role:update,role:delete,role:view)")
    @TableField("permission_str")
    private String permissionStr;

    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty(value = "权限排序")
    @TableField("permission_order")
    private Integer permissionOrder;

    @ApiModelProperty("隐藏或者显示")
    @TableField("is_view")
    private Boolean isView;

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
