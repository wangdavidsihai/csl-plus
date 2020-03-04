package com.csl.plus.oms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.oms.entity.OmsOrderBill;
import com.csl.plus.oms.mapper.OmsOrderBillMapper;
import com.csl.plus.oms.service.IOmsOrderBillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("omsOrderBillService")
public class OmsOrderBillServiceImpl extends ServiceImpl<OmsOrderBillMapper, OmsOrderBill> implements IOmsOrderBillService {

    @Resource
    private OmsOrderBillMapper omsOrderBillMapper;

    @Transactional
    public boolean saves(OmsOrderBill entity) {
        entity.setCreateDate(new Date());
        omsOrderBillMapper.insert(entity);
        return true;
    }

}