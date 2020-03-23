package com.csl.plus.res.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源类别表
 *
 * @author David
 * @email
 * @date 2020-03-20 21:16:19
 */
@Data
@TableName("res_category")
public class ResCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 上机分类的编号：0表示一级分类
     */
    private Integer parentId = 0;
    /**
     *
     */
    private String name;
    /**
     * 分类级别：0->1级；1->2级
     */
    private Integer level = 1;
    /**
     * 显示状态：0->不显示；1->显示
     */
    private Integer showStatus;
    /**
     *
     */
    private Integer sort;
    /**
     * 图标
     */
    private String icon;
    /**
     *
     */
    private String keywords;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createDate;

}
