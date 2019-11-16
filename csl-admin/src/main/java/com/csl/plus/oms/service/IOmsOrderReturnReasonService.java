package com.csl.plus.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.oms.entity.OmsOrderReturnReason;

import java.util.List;

/**
 * <p>
 * 退货原因表 服务类
 * </p>
 *
 * @since 2019-04-17
 */
public interface IOmsOrderReturnReasonService extends IService<OmsOrderReturnReason> {
    /**
     * 批量修改退货原因状态
     */
    int updateStatus(List<Long> ids, Integer status);
}
