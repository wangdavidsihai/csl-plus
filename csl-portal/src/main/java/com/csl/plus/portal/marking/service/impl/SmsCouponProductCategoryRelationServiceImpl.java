package com.csl.plus.portal.marking.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.marking.entity.SmsCouponProductCategoryRelation;
import com.csl.plus.marking.mapper.SmsCouponProductCategoryRelationMapper;
import com.csl.plus.portal.marking.service.ISmsCouponProductCategoryRelationService;

/**
 * <p>
 * 优惠券和产品分类关系表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class SmsCouponProductCategoryRelationServiceImpl
		extends ServiceImpl<SmsCouponProductCategoryRelationMapper, SmsCouponProductCategoryRelation>
		implements ISmsCouponProductCategoryRelationService {

}
