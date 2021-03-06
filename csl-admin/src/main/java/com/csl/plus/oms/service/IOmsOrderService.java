package com.csl.plus.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.oms.entity.OmsOrder;
import com.csl.plus.oms.vo.OmsMoneyInfoParam;
import com.csl.plus.oms.vo.OmsOrderDeliveryParam;
import com.csl.plus.oms.vo.OmsReceiverInfoParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @since 2019-04-17
 */
public interface IOmsOrderService extends IService<OmsOrder> {
    /**
     * 修改订单收货人信息
     */
    @Transactional
    int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam);

    /**
     * 修改订单费用信息
     */
    @Transactional
    int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam);

    /**
     * 修改订单备注
     */
    @Transactional
    int updateNote(Long id, String note, Integer status);


    /**
     * 批量发货
     */
    @Transactional
    int delivery(List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 批量关闭订单
     */
    @Transactional
    int close(List<Long> ids, String note);

    /**
     * 派单
     */
    @Transactional
    int updateAssignment(Long id, Long pwid, String note);
}
