package com.csl.plus.portal.marking.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.marking.entity.SmsCouponProductRelation;
import com.csl.plus.marking.mapper.SmsCouponProductRelationMapper;
import com.csl.plus.portal.marking.service.ISmsCouponProductRelationService;

/**
 * <p>
 * 优惠券和产品的关系表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class SmsCouponProductRelationServiceImpl
		extends ServiceImpl<SmsCouponProductRelationMapper, SmsCouponProductRelation>
		implements ISmsCouponProductRelationService {

}
