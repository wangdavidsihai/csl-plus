package com.csl.plus.portal.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.csl.plus.portal.oms.service.IOmsOrderService;
import com.csl.plus.utils.CommonResult;

@Component
public class OrderTimeOutCancelTask {
	private Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);
	@Autowired
	private IOmsOrderService portalOrderService;

	/**
	 * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
	 * 每10分钟扫描一次，扫描设定超时时间之前下的订单，如果没支付则取消该订单
	 */
	@Scheduled(cron = "0 0/10 * ? * ?")
	private void cancelTimeOutOrder() {
		CommonResult result = portalOrderService.cancelTimeOutOrder();
		LOGGER.info("取消订单，并根据sku编号释放锁定库存:{}", result);
	}
}
