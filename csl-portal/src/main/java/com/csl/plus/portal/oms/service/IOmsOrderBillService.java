package com.csl.plus.portal.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.oms.entity.OmsOrderBill;


/**
 * 发票表
 *
 * @author David
 * @email
 * @date 2020-03-04 10:37:32
 */
public interface IOmsOrderBillService extends IService<OmsOrderBill> {


    boolean saves(OmsOrderBill entity);
}

