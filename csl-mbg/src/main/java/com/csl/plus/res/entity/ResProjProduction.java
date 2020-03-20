package com.csl.plus.res.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目表
 *
 * @author David
 * @email
 * @date 2020-02-15 22:12:07
 */
@Data
@TableName("res_proj_production")
public class ResProjProduction implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 类别ID
     */
    private Long categoryId;
    /**
     * 标题
     */
    private String title;
    /**
     * 关键字
     */
    private String keywords;
    /**
     * 描述、摘要
     */
    private String description;
    /**
     * 项目编号
     */
    private String proRef;
    /**
     * 地点id
     */
    private Long cityId;
    /**
     * 地点
     */
    private String city;
    /**
     * 产品图片
     */
    private String image;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     *
     */
    private Date endDate;
    /**
     *
     */
    private String status;
    /**
     *
     */
    private Date startDate;
    /**
     *
     */
    private String sysGroup;

    @TableField(exist = false)
    private ResCategory resCategory;

}
