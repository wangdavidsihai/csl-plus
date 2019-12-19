package com.csl.plus.cms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * Company
 * 
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-19 20:53:33
 */
@Data
@TableName("cms_company")
public class CmsCompany implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 栏目编号
	 */
	private String category;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 子标题
	 */
	private String subtitle;
	/**
	 * 文章图片
	 */
	private String image;
	/**
	 * 关键字
	 */
	private String keywords;
	/**
	 * 所有者
	 */
	private String author;
	/**
	 * 描述、摘要
	 */
	private String description;
	/**
	 * 地址
	 */
	private String address;
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

}
