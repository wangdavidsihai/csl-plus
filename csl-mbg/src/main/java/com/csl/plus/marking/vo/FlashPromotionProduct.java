package com.csl.plus.marking.vo;

import java.math.BigDecimal;

import com.csl.plus.pms.entity.PmsProduct;

import lombok.Getter;
import lombok.Setter;

/**
 * 秒杀信息和商品对象封装
 */
@Getter
@Setter
public class FlashPromotionProduct extends PmsProduct {
	private BigDecimal flashPromotionPrice;
	private Integer flashPromotionCount;
	private Integer flashPromotionLimit;
}
