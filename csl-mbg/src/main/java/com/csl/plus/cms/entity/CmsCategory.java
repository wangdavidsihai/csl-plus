package com.csl.plus.cms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 栏目表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 14:02:33
 */
@Data
@TableName("cms_category")
public class CmsCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId
    private String id;
    /**
     * 父级编号
     */
    private String parentId;
    /**
     * 所有父级编号
     */
    private String parentIds;
    /**
     * 站点编号
     */
    private String siteId;
    /**
     * 归属机构
     */
    private String officeId;
    /**
     * 栏目模块
     */
    private String module;
    /**
     * 栏目名称
     */
    private String name;
    /**
     * 栏目图片
     */
    private String image;
    /**
     * 链接
     */
    private String href;
    /**
     * 目标
     */
    private String target;
    /**
     * 描述
     */
    private String description;
    /**
     * 关键字
     */
    private String keywords;
    /**
     * 排序（升序）
     */
    private Integer sort;
    /**
     * 是否在导航中显示
     */
    private String inMenu;
    /**
     * 是否在分类页中显示列表
     */
    private String inList;
    /**
     * 展现方式
     */
    private String showModes;
    /**
     * 是否允许评论
     */
    private String allowComment;
    /**
     * 是否需要审核
     */
    private String isAudit;
    /**
     * 自定义列表视图
     */
    private String customListView;
    /**
     * 自定义内容视图
     */
    private String customContentView;
    /**
     * 视图配置
     */
    private String viewConfig;
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
