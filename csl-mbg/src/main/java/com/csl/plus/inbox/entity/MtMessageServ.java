package com.csl.plus.inbox.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * 服务端站内信
 *
 * @author David
 * @email
 * @date 2020-01-31 17:46:11
 */
@Data
@TableName("mt_message_serv")
public class MtMessageServ implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.UUID)
    private String id;
    /**
     * 发送者ID
     */
    @NotEmpty(message = "请求者id不能为空")
    private String sendid;
    /**
     * 接收者ID
     */
    private String recid;
    /**
     * 标题
     */
    @NotEmpty(message = "标题不能为空")
    private String subject;
    /**
     * 内容
     */
    @NotEmpty(message = "内容不能为空")
    private String message;
    /**
     * 关联 id
     */
    @NotEmpty(message = "资源id不能为空")
    private String refid;
    /**
     * 发送时间
     */
    private Date sendDate;
    /**
     * 状态
     */
    private String status;
    /**
     * 删除标识
     */
    private String delFlag;
    /**
     * 渠道
     */
    private String channel;
    @NotEmpty(message = "类型分组不能为空")
    private String sysGroup;

}
