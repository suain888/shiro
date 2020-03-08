package com.dmj.admincommon.pojo.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author dongzhang
 * @since 2020-01-26
 */
@Data
public class SysUserVO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "用户密码")
    @TableField("user_password")
    private String userPassword;

    @ApiModelProperty(value = "盐")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "真实姓名")
    @TableField("nick_name")
    private String nickName;

    @TableField("company_id")
    private String companyId;

    @ApiModelProperty(value = "公司名称")
    @TableField("company_name")
    private String companyName;

    @ApiModelProperty(value = "部门ID")
    @TableField("department_id")
    private String departmentId;

    @ApiModelProperty(value = "部门名称")
    @TableField("department_name")
    private String departmentName;

    @ApiModelProperty(value = "岗位")
    @TableField("job")
    private String job;

    @ApiModelProperty(value = "手机号")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "座机")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "头像地址")
    @TableField("avatar_url")
    private String avatarUrl;

    @ApiModelProperty(value = "保密字段（0不能，1能）")
    @TableField("secret")
    private Boolean secret;

    @ApiModelProperty(value = "可用状态 0：可用 1：禁用")
    @TableField("delete_flag")
    @TableLogic
    private Boolean deleteFlag;

    @ApiModelProperty(value = "是否锁定 1：锁定 0：未锁定")
    @TableField("locked")
    private Boolean locked;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "创建人账号")
    @TableField("creator")
    private String creator;

    @ApiModelProperty(value = "")
    @TableField("updater")
    private String updater;

    private List<SysRoleVO> roleVOList;
}
