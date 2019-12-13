package com.csl.plus.portal.marking.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.marking.entity.SmsHomeRecommendProduct;
import com.csl.plus.marking.mapper.SmsHomeRecommendProductMapper;
import com.csl.plus.portal.marking.service.ISmsHomeRecommendProductService;

/**
 * <p>
 * 人气推荐商品表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class SmsHomeRecommendProductServiceImpl extends
		ServiceImpl<SmsHomeRecommendProductMapper, SmsHomeRecommendProduct> implements ISmsHomeRecommendProductService {

}
