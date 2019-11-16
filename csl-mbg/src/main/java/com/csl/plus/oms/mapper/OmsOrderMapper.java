package com.csl.plus.oms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.plus.oms.entity.OmsOrder;
import com.csl.plus.oms.entity.OmsOrderItem;
import com.csl.plus.oms.vo.OmsOrderDeliveryParam;
import com.csl.plus.oms.vo.OmsOrderDetail;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 *  
 * @since 2019-04-17
 */
public interface OmsOrderMapper extends BaseMapper<OmsOrder> {

    /**
     * 修改 pms_sku_stock表的锁定库存及真实库存
     */
    int updateSkuStock(@Param("itemList") List<OmsOrderItem> orderItemList);

    /**
     * 获取超时订单
     *
     * @param minute 超时时间（分）
     */
    List<OmsOrderDetail> getTimeOutOrders(@Param("minute") Integer minute);

    /**
     * 批量修改订单状态
     */
    int updateOrderStatus(@Param("ids") List<Long> ids, @Param("status") Integer status);

    /**
     * 解除取消订单的库存锁定
     */
    int releaseSkuStockLock(@Param("itemList") List<OmsOrderItem> orderItemList);

    /**
     * 批量发货
     */
    int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);
}
