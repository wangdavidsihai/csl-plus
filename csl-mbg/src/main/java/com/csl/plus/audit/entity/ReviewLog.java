package com.csl.plus.audit.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 审核记录
 *
 * @author David
 * @email
 * @date 2020-03-21 20:40:37
 */
@Data
@TableName("review_log")
public class ReviewLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     *
     */
    private Long refId;
    /**
     *
     */
    private Date createDate;
    /**
     * 审核人
     */
    private String approver;
    /**
     *
     */
    private Integer status;
    /**
     * 反馈详情
     */
    private String detail;
    /**
     * system group
     */
    private String sysGroup;

}
