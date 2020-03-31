package com.csl.plus.ums.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 后台用户角色和权限关系表
 *
 * @author David
 * @email
 * @date 2020-02-16 21:48:43
 */
@Data
@TableName("ums_member_role_permission")
public class UmsMemberRolePermission implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     *
     */
    private Long roleId;
    /**
     *
     */
    private Long permissionId;

}
