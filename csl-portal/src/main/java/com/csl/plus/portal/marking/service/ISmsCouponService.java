package com.csl.plus.portal.marking.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.marking.entity.SmsCoupon;
import com.csl.plus.marking.entity.SmsCouponHistory;
import com.csl.plus.marking.vo.SmsCouponHistoryDetail;
import com.csl.plus.oms.vo.CartPromotionItem;
import com.csl.plus.utils.CommonResult;

/**
 * <p>
 * 优惠卷表 服务类
 * </p>
 *
 * @since 2019-04-19
 */
public interface ISmsCouponService extends IService<SmsCoupon> {

	/**
	 * 会员添加优惠券
	 */
	@Transactional
	CommonResult add(Long couponId);

	/**
	 * 获取优惠券列表
	 *
	 * @param useStatus 优惠券的使用状态
	 */
	List<SmsCouponHistory> list(Integer useStatus);

	/**
	 * 根据购物车信息获取可用优惠券
	 */
	List<SmsCouponHistoryDetail> listCart(List<CartPromotionItem> cartItemList, Integer type);

	List<SmsCoupon> selectNotRecive(Long memberId);

	List<SmsCoupon> selectRecive(Long memberId);

	List<SmsCoupon> selectNotRecive();
}
