package com.csl.plus.portal.pms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.pms.entity.PmsProductLadder;
import com.csl.plus.pms.mapper.PmsProductLadderMapper;
import com.csl.plus.portal.pms.service.IPmsProductLadderService;

/**
 * <p>
 * 产品阶梯价格表(只针对同商品) 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class PmsProductLadderServiceImpl extends ServiceImpl<PmsProductLadderMapper, PmsProductLadder>
		implements IPmsProductLadderService {

}
