package com.csl.plus.rms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 技术应用领域表
 * 
 * @author David
 * @email 
 * @date 2020-01-30 10:58:45
 */
@Data
@TableName("rms_tech_requirement_app_area")
public class TechRequirementAppArea implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 关键字
	 */
	private String keywords;
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

}
