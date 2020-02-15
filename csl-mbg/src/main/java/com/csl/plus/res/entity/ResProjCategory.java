package com.csl.plus.res.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 项目类别表
 * 
 * @author David
 * @email 
 * @date 2020-02-15 22:12:07
 */
@Data
@TableName("res_proj_category")
public class ResProjCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 上机分类的编号：0表示一级分类
	 */
	private Long parentId;
	/**
	 * 
	 */
	private String name;
	/**
	 * 分类级别：0->1级；1->2级
	 */
	private Integer level;
	/**
	 * 是否显示在导航栏：0->不显示；1->显示
	 */
	private Integer navStatus;
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
