package com.csl.plus.oms.vo;

import java.util.List;

import com.csl.plus.oms.entity.OmsOrder;
import com.csl.plus.oms.entity.OmsOrderItem;
import com.csl.plus.oms.entity.OmsOrderOperateHistory;

import lombok.Getter;
import lombok.Setter;

/**
 * 订单详情信息
 */
public class OmsOrderDetail extends OmsOrder {
	@Getter
	@Setter
	private List<OmsOrderItem> orderItemList;
	@Getter
	@Setter
	private List<OmsOrderOperateHistory> historyList;
}
