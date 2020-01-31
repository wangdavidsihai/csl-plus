package com.csl.plus.inbox.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 消息通知表
 * 
 * @author David
 * @email 
 * @date 2020-01-31 17:46:11
 */
@Data
@TableName("mt_message")
public class MtMessage implements Serializable {
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
	 * 消息ID
	 */
	private String msgid;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 删除标识
	 */
	private String delFlag;

}
