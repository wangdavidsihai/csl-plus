package com.csl.plus.cms.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 专家表
 * 
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 13:11:01
 */
@Data
@TableName("cms_expert_data")
public class CmsExpertData implements Serializable {
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
	 * 状态标记
	 */
	private String status;

}
