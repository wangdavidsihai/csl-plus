package com.csl.plus.rms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 金融需求表
 *
 * @author David
 * @email
 * @date 2020-01-30 16:15:33
 */
@Data
@TableName("rms_fin_requirement")
public class FinRequirement implements Serializable {
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
     * 应用领域ID
     */
    private Long appAreaId;
    /**
     * 先进程度
     */
    private String mainFunc;
    /**
     * 型号
     */
    private String model;
    /**
     * 需求参数
     */
    private String finParam;
    /**
     * 供应商
     */
    private String provider;
    /**
     * 供应商地址
     */
    private String providerAddr;
    /**
     * 联系方式
     */
    private String contact;
    /**
     * 邮编
     */
    private String postCode;
    /**
     * 产品图片
     */
    private String image;
    /**
     * 产品价格
     */
    private String price;
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
    private String mobile;
    /**
     *
     */
    private String email;
    /**
     *
     */
    private String sysGroup;

}
