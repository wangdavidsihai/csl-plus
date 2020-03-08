package com.csl.plus.ums.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台用户角色表
 *
 * @author David
 * @email
 * @date 2020-02-16 21:48:43
 */
@Data
@TableName("ums_member_role")
public class UmsMemberRole implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 后台用户数量
     */
    private Integer adminCount;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 启用状态：0->禁用；1->启用
     */
    private Integer status;
    /**
     *
     */
    private Integer sort;

    @TableField(exist = false)
    private String menuIds;

}
