package com.csl.plus.res.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目表详细信息
 *
 * @author David
 * @email
 * @date 2020-02-15 22:12:07
 */
@Data
@TableName("res_project_data")
public class ResProjectData implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 项目ID
     */
    private Long proId;
    /**
     * 标题
     */
    private String title;
    /**
     * 关键字
     */
    private String keywords;
    /**
     * 详细信息
     */
    private String content;
    /**
     * 项目编号
     */
    private String proRef;
    /**
     * 项目单位
     */
    private String company;
    /**
     * 技术参数
     */
    private String techParam;
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

}
