package com.csl.plus.res.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 技术应用领域表
 *
 * @author David
 * @email
 * @date 2020-02-29 11:32:27
 */
@Data
@TableName("res_technical_app_area_mtoc")
public class ResTechnicalAppAreaMtoc implements Serializable {
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
