package com.csl.plus.marking.vo;

import com.csl.plus.marking.entity.SmsFlashPromotionSession;

import lombok.Getter;
import lombok.Setter;

/**
 * 包含商品数量的场次信息
 */
public class SmsFlashPromotionSessionDetail extends SmsFlashPromotionSession {
	@Setter
	@Getter
	private Integer productCount;
}
