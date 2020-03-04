package com.csl.plus.oms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 订单中所包含的商品
 * </p>
 *
 * @since 2019-04-17
 */
@TableName("oms_order_item")
@Data
public class OmsOrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单id
     */
    @TableField("order_id")
    private Long orderId;

    /**
     * 订单编号
     */
    @TableField("order_sn")
    private String orderSn;

    @TableField("product_id")
    private Long productId;

    @TableField("product_pic")
    private String productPic;

    @TableField("product_name")
    private String productName;

    @TableField("product_sn")
    private String productSn;

    /**
     * 销售价格
     */
    @TableField("product_price")
    private BigDecimal productPrice;

    /**
     * 购买数量
     */
    @TableField("product_quantity")
    private Integer productQuantity;

    /**
     * 商品sku编号
     */
    @TableField("product_sku_id")
    private Long productSkuId;

    /**
     * 商品sku条码
     */
    @TableField("product_sku_code")
    private String productSkuCode;

    /**
     * 商品分类id
     */
    @TableField("product_category_id")
    private Long productCategoryId;
    /**
     * 对接产品URL
     */
    private String url;
//    productCategoryId/**
//     * 商品的销售属性
//     */
//    private String sp1;
//
//    private String sp2;
//
//    private String sp3;
//
//    /**
//     * 商品促销名称
//     */
//    @TableField("promotion_name")
//    private String promotionName;
//
//    /**
//     * 商品促销分解金额
//     */
//    @TableField("promotion_amount")
//    private BigDecimal promotionAmount;
//
//    /**
//     * 优惠券优惠分解金额
//     */
//    @TableField("coupon_amount")
//    private BigDecimal couponAmount;
//
//    /**
//     * 积分优惠分解金额
//     */
//    @TableField("integration_amount")
//    private BigDecimal integrationAmount;
//
    /**
     * 该商品经过优惠后的分解金额
     */
    @TableField("real_amount")
    private BigDecimal realAmount;


    @Override
    public String toString() {
        return "OmsOrderItem{" +
                ", id=" + id +
                ", orderId=" + orderId +
                ", orderSn=" + orderSn +
                ", productId=" + productId +
                ", productPic=" + productPic +
                ", productName=" + productName +
                ", productSn=" + productSn +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                ", productSkuId=" + productSkuId +
                ", productSkuCode=" + productSkuCode +
                ", productCategoryId=" + productCategoryId +
//                ", sp1=" + sp1 +
//                ", sp2=" + sp2 +
//                ", sp3=" + sp3 +
//                ", promotionName=" + promotionName +
//                ", promotionAmount=" + promotionAmount +
//                ", couponAmount=" + couponAmount +
//                ", integrationAmount=" + integrationAmount +
                ", realAmount=" + realAmount +
//                ", giftIntegration=" + giftIntegration +
//                ", giftGrowth=" + giftGrowth +
//                ", productAttr=" + productAttr +
                "}";
    }
}
