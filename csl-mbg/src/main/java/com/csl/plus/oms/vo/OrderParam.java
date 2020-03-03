package com.csl.plus.oms.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * 生成订单时传入的参数
 */
@Data
public class OrderParam implements java.io.Serializable {
    private Integer payType;
    private String type; // 1 商品详情 2 勾选购物车 3全部购物车的商品
    private Integer addressId;

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
