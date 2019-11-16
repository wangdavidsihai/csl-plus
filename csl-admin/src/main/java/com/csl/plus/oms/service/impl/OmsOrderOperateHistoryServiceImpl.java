package com.csl.plus.oms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.oms.entity.OmsOrderOperateHistory;
import com.csl.plus.oms.mapper.OmsOrderOperateHistoryMapper;
import com.csl.plus.oms.service.IOmsOrderOperateHistoryService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单操作历史记录 服务实现类
 * </p>
 *
 * @since 2019-04-17
 */
@Service
public class OmsOrderOperateHistoryServiceImpl extends ServiceImpl<OmsOrderOperateHistoryMapper, OmsOrderOperateHistory> implements IOmsOrderOperateHistoryService {

}
