package com.csl.plus.portal.pms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.pms.entity.PmsSkuStock;
import com.csl.plus.pms.mapper.PmsSkuStockMapper;
import com.csl.plus.portal.pms.service.IPmsSkuStockService;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class PmsSkuStockServiceImpl extends ServiceImpl<PmsSkuStockMapper, PmsSkuStock> implements IPmsSkuStockService {

}
