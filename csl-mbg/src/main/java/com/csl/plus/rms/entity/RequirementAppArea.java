package com.csl.plus.rms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 资源应用领域表
 * 
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2020-01-04 20:03:44
 */
@Data
@TableName("rms_requirement_app_area")
public class RequirementAppArea implements Serializable {
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
