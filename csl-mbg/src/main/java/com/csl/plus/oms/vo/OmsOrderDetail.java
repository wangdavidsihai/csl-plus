package com.csl.plus.oms.vo;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.csl.plus.oms.entity.OmsOrder;
import com.csl.plus.oms.entity.OmsOrderItem;
import com.csl.plus.oms.entity.OmsOrderOperateHistory;

/**
 * 订单详情信息
 * https://github.com/shenzhuan/mallplus on 2018/10/11.
 */
public class OmsOrderDetail extends OmsOrder {
    @Getter
    @Setter
    private List<OmsOrderItem> orderItemList;
    @Getter
    @Setter
    private List<OmsOrderOperateHistory> historyList;
}
