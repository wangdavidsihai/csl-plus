package com.csl.plus.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章详表
 *
 * @author David
 * @email wangdavidsihai@gmail.com
 * @date 2019-12-22 14:02:33
 */
@Data
@TableName("cms_article_data")
public class CmsArticleData implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 文章来源
     */
    private String author;
    /**
     * 相关文章
     */
    private String relation;
    /**
     * 标题
     */
    private String title;
    /**
     * 是否允许评论
     */
    private String allowComment;
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
     * 状态标记
     */
    private Integer status;
}
