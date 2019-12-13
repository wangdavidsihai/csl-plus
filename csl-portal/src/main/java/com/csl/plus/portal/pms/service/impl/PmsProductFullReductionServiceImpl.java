package com.csl.plus.portal.pms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.pms.entity.PmsProductFullReduction;
import com.csl.plus.pms.mapper.PmsProductFullReductionMapper;
import com.csl.plus.portal.pms.service.IPmsProductFullReductionService;

/**
 * <p>
 * 产品满减表(只针对同商品) 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class PmsProductFullReductionServiceImpl extends
		ServiceImpl<PmsProductFullReductionMapper, PmsProductFullReduction> implements IPmsProductFullReductionService {

}
