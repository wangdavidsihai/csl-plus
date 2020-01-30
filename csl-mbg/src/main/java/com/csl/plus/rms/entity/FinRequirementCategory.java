package com.csl.plus.rms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 金融类别表
 * 
 * @author David
 * @email 
 * @date 2020-01-30 16:15:33
 */
@Data
@TableName("rms_fin_requirement_category")
public class FinRequirementCategory implements Serializable {
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
	 * 备注信息
	 */
	private String remarks;
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
