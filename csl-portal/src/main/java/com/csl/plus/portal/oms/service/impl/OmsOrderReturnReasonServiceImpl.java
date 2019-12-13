package com.csl.plus.portal.oms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.oms.entity.OmsOrderReturnReason;
import com.csl.plus.oms.mapper.OmsOrderReturnReasonMapper;
import com.csl.plus.portal.oms.service.IOmsOrderReturnReasonService;

/**
 * <p>
 * 退货原因表 服务实现类
 * </p>
 *
 * @since 2019-04-17
 */
@Service
public class OmsOrderReturnReasonServiceImpl extends ServiceImpl<OmsOrderReturnReasonMapper, OmsOrderReturnReason>
		implements IOmsOrderReturnReasonService {

}
