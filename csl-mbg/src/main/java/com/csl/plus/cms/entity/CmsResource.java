package com.csl.plus.cms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 资源表
 * 
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 13:11:01
 */
@Data
@TableName("cms_resource")
public class CmsResource implements Serializable {
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

}
