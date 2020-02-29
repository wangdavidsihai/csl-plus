package com.csl.plus.portal.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UmsMemberVO implements java.io.Serializable {

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
    private String personalizedSignature;

    /**
     * 用户来源 1 小程序 2 公众号 3 页面
     */
    private Integer sourceType;

    private String avatar;

    private String confirmpassword;
    // email
    private String email;
    // category 1,普通企业 2 政府 3 军工企业 4 个人 5 其他组织
    private String category;
    // 企业主体名称
    private String companyName;
    // 工商注册号
    private String companyCert;
    //2FA
    private String authCode;


}
