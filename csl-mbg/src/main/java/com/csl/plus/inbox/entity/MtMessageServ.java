package com.csl.plus.inbox.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

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
	private String sendid;
	/**
	 * 接收者ID
	 */
	private String recid;
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

}
