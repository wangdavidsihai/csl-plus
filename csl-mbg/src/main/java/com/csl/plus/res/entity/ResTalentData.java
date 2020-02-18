package com.csl.plus.res.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 专家表
 *
 * @author David
 * @email
 * @date 2020-02-18 21:43:07
 */
@Data
@TableName("res_talent_data")
public class ResTalentData implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 所属学科
     */
    private Integer categoryId;
    /**
     * 照片
     */
    private String image;
    /**
     * 关键字
     */
    private String keywords;
    /**
     * 居住地址
     */
    private String addr;
    /**
     * 单位
     */
    private String company;
    /**
     * 学历
     */
    private String education;
    /**
     * 毕业院校
     */
    private String graduateSchool;
    /**
     * 描述、摘要
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
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 备注信息
     */
    private String remarks;
    /**
     * 删除标记
     */
    private String delFlag;
    /**
     *
     */
    private String status;
    /**
     *
     */
    private Long talId;

}
