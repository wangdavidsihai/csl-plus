package com.csl.plus.ums.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 后台用户和角色关系表
 *
 * @author David
 * @email
 * @date 2020-02-17 17:03:22
 */
@Data
@TableName("ums_member_user_role")
public class UmsMemberUserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     *
     */
    private Long userId;
    /**
     *
     */
    private Long roleId;

}
