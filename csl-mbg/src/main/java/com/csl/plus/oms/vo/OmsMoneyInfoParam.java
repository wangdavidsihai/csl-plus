package com.csl.plus.oms.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * 修改订单费用信息参数
 */
@Getter
@Setter
public class OmsMoneyInfoParam {
	private Long orderId;
	private BigDecimal freightAmount;
	private BigDecimal discountAmount;
	private Integer status;
}
