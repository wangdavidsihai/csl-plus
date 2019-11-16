package com.csl.plus.marking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.marking.entity.SmsCouponHistory;
import com.csl.plus.marking.mapper.SmsCouponHistoryMapper;
import com.csl.plus.marking.service.ISmsCouponHistoryService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券使用、领取历史表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class SmsCouponHistoryServiceImpl extends ServiceImpl<SmsCouponHistoryMapper, SmsCouponHistory> implements ISmsCouponHistoryService {

}
