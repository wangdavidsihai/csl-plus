package com.csl.plus.oms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @since 2019-04-17
 */
@Data
@TableName("oms_order")
public class OmsOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    List<OmsOrderItem> orderItemList;
    /**
     * 订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_id")
    private Long memberId;
    /**
     * 订单编号
     */
    @TableField("order_sn")
    private String orderSn;

    /**
     * 提交时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 用户帐号
     */
    @TableField("member_username")
    private String memberUsername;

    /**
     * 订单总金额
     */
    @TableField("total_amount")
    private BigDecimal totalAmount;

    /**
     * 应付金额（实际支付金额）
     */
    @TableField("pay_amount")
    private BigDecimal payAmount;

    /**
     * 管理员后台调整订单使用的折扣金额
     */
    @TableField("discount_amount")
    private BigDecimal discountAmount;

    /**
     * 支付方式：0->未支付；1->支付宝；2->微信
     */
    @TableField("pay_type")
    private Integer payType;

    /**
     * 订单来源：0->PC订单；1->app订单
     */
    @TableField("source_type")
    private Integer sourceType;

    /**
     * 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     */
    private Integer status;

    /**
     * 订单类型：0->正常订单；1->秒杀订单
     */
    @TableField("order_type")
    private Integer orderType;

    /**
     * 物流公司(配送方式)
     */
    @TableField("delivery_company")
    private String deliveryCompany;

    /**
     * 物流单号
     */
    @TableField("delivery_sn")
    private String deliverySn;


    /**
     * 订单备注
     */
    private String note;

    /**
     * 确认收货状态：0->未确认；1->已确认
     */
    @TableField("confirm_status")
    private Integer confirmStatus;

    /**
     * 删除状态：0->未删除；1->已删除
     */
    @TableField("delete_status")
    private Integer deleteStatus;

    /**
     * 下单时使用的积分
     */
    @TableField("use_integration")
    private Integer useIntegration;

    /**
     * 支付时间
     */
    @TableField("payment_time")
    private Date paymentTime;

    /**
     * 发货时间
     */
    @TableField("delivery_time")
    private Date deliveryTime;

    /**
     * 确认收货时间
     */
    @TableField("receive_time")
    private Date receiveTime;

    /**
     * 评价时间
     */
    @TableField("comment_time")
    private Date commentTime;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;


    @TableField("supply_id")
    private Long supplyId;

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
    @NotEmpty(message = "类型分组不能为空")
    private String sysGroup;
    /**
     * 渠道
     */
    private String channel;


}
