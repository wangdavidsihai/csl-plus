package com.csl.plus.marking.vo;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import com.csl.plus.pms.entity.PmsProduct;

/**
 * 秒杀信息和商品对象封装
 * https://github.com/shenzhuan/mallplus on 2019/1/28.
 */
@Getter
@Setter
public class FlashPromotionProduct extends PmsProduct {
    private BigDecimal flashPromotionPrice;
    private Integer flashPromotionCount;
    private Integer flashPromotionLimit;
}
