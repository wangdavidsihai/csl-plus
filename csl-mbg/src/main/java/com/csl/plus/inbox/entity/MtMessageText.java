package com.csl.plus.inbox.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息内容表
 *
 * @author David
 * @email
 * @date 2020-01-31 17:46:11
 */
@Data
@TableName("mt_message_text")
public class MtMessageText implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.UUID)
    private String id;
    /**
     * 标题
     */
    private String subject;
    /**
     * 内容
     */
    private String message;
    /**
     * 关联 id
     */
    private String refid;
    /**
     * URL
     */
    private String url;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 状态
     */
    private Integer status;

}
