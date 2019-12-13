package com.csl.plus.portal.oms.service;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.oms.entity.OmsOrder;
import com.csl.plus.oms.vo.ConfirmOrderResult;
import com.csl.plus.oms.vo.GroupAndOrderVo;
import com.csl.plus.oms.vo.OrderParam;
import com.csl.plus.oms.vo.TbThanks;
import com.csl.plus.ums.entity.UmsMember;
import com.csl.plus.utils.CommonResult;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @since 2019-04-17
 */
public interface IOmsOrderService extends IService<OmsOrder> {

	Object preSingelOrder(GroupAndOrderVo orderParam);

	Object generateSingleOrder(GroupAndOrderVo orderParam, UmsMember member);

	/**
	 * 根据用户购物车信息生成确认单信息
	 */
	ConfirmOrderResult generateConfirmOrder();

	/**
	 * 根据提交信息生成订单
	 */
	@Transactional
	CommonResult generateOrder(OrderParam orderParam);

	/**
	 * 支付成功后的回调
	 */
	@Transactional
	CommonResult paySuccess(Long orderId);

	/**
	 * 自动取消超时订单
	 */
	@Transactional
	CommonResult cancelTimeOutOrder();

	/**
	 * 取消单个超时订单
	 */
	@Transactional
	void cancelOrder(Long orderId);

	/**
	 * 发送延迟消息取消订单
	 */
	void sendDelayMessageCancelOrder(Long orderId);

	ConfirmOrderResult submitPreview(OrderParam orderParam);

	int payOrder(TbThanks tbThanks);
}
