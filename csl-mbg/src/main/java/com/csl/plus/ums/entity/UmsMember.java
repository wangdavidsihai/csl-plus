package com.csl.plus.ums.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * <p>
 * 会员表
 * </p>
 *
 * 
 * @since 2019-04-19
 */
@Data
@TableName("ums_member")
public class UmsMember implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@TableField("member_level_id")
	private Long memberLevelId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 帐号启用状态:A->正常；C->关闭; R->审核中; S->暂停
	 */
	private String status;
	/**
	 * 注册时间
	 */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 头像
	 */
	private String icon;

	/**
	 * 性别：0->未知；1->男；2->女
	 */
	private Integer gender;

	/**
	 * 生日
	 */
	private Date birthday;

	/**
	 * 所做城市
	 */
	private String city;

	/**
	 * 职业
	 */
	private String job;

	/**
	 * 个性签名
	 */
	@TableField("personalized_signature")
	private String personalizedSignature;

	/**
	 * 用户来源 1 小程序 2 公众号 3 页面
	 */
	@TableField("source_type")
	private Integer sourceType;

	/**
	 * 积分
	 */
	private Integer integration;

	/**
	 * 成长值
	 */
	private Integer growth;

	/**
	 * 剩余抽奖次数
	 */
	@TableField("luckey_count")
	private Integer luckeyCount;
	/**
	 * 历史积分数量
	 */
	@TableField("history_integration")
	private Integer historyIntegration;

	private String avatar;

	@TableField("weixin_openid")
	private String weixinOpenid;

	private Long invitecode;
	/**
	 * 余额
	 */
	private BigDecimal blance;

	@TableField("school_id")
	private Long schoolId;
	@TableField(exist = false)
	private String confimpassword;
	// email
	private String email;
	// category 1,普通企业 2 政府 3 军工企业 4 个人 5 其他组织
	private String category;
	// 企业主体名称
	@TableField("company_name")
	private String companyName;
	// 工商注册号
	@TableField("company_cert")
	private String companyCert;

}
