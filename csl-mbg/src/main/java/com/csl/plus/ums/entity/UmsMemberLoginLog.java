package com.csl.plus.ums.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员登录记录
 *
 * @author David
 * @email
 * @date 2020-03-05 09:00:12
 */
@Data
@TableName("ums_member_login_log")
public class UmsMemberLoginLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     *
     */
    private String memberName;
    /**
     *
     */
    private Date createDate;
    /**
     *
     */
    private String ip;
    /**
     *
     */
    private String city;
    /**
     *
     */
    private String province;
    /**
     * 登录渠道
     */
    private String channel;

    private String appVersion;

}
