package com.csl.plus.cms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 14:02:33
 */
@Data
@TableName("cms_notice")
public class CmsNotice implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId
    private String id;
    /**
     * 栏目编号
     */
    private String categoryId;
    /**
     * 标题
     */
    private String title;
    /**
     * 文章链接
     */
    private String link;
    /**
     * 标题颜色
     */
    private String color;
    /**
     * 文章图片
     */
    private String image;
    /**
     * 关键字
     */
    private String keywords;
    /**
     * 描述、摘要
     */
    private String description;
    /**
     * 权重，越大越靠前
     */
    private Integer weight;
    /**
     * 权重期限
     */
    private Date weightDate;
    /**
     * 点击数
     */
    private Integer hits;
    /**
     * 推荐位，多选
     */
    private String posid;
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
    /**
     * 状态标记
     */
    private Integer status;

}
