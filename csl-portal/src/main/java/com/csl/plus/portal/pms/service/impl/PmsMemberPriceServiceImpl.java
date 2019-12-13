package com.csl.plus.portal.pms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.pms.entity.PmsMemberPrice;
import com.csl.plus.pms.mapper.PmsMemberPriceMapper;
import com.csl.plus.portal.pms.service.IPmsMemberPriceService;

/**
 * <p>
 * 商品会员价格表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class PmsMemberPriceServiceImpl extends ServiceImpl<PmsMemberPriceMapper, PmsMemberPrice>
		implements IPmsMemberPriceService {

}
