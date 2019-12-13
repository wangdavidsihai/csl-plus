package com.csl.plus.portal.oms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.oms.entity.OmsOrderItem;
import com.csl.plus.oms.mapper.OmsOrderItemMapper;
import com.csl.plus.portal.oms.service.IOmsOrderItemService;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @since 2019-04-17
 */
@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem>
		implements IOmsOrderItemService {

}
