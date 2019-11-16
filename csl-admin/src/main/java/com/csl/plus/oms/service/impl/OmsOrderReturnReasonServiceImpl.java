package com.csl.plus.oms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.oms.entity.OmsOrderReturnReason;
import com.csl.plus.oms.mapper.OmsOrderReturnReasonMapper;
import com.csl.plus.oms.service.IOmsOrderReturnReasonService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 退货原因表 服务实现类
 * </p>
 *
 * @since 2019-04-17
 */
@Service
public class OmsOrderReturnReasonServiceImpl extends ServiceImpl<OmsOrderReturnReasonMapper, OmsOrderReturnReason> implements IOmsOrderReturnReasonService {

    @Override
    public int updateStatus(List<Long> ids, Integer status) {
        if (!status.equals(0) && !status.equals(1)) {
            return 0;
        }
        OmsOrderReturnReason record = new OmsOrderReturnReason();
        record.setStatus(status);
         this.update(record, new QueryWrapper<>(record).in("id",ids));
         return 1;
    }
}
