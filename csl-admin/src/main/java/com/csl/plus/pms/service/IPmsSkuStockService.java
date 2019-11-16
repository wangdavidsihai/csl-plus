package com.csl.plus.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.pms.entity.PmsSkuStock;

import java.util.List;

/**
 * <p>
 * sku的库存 服务类
 * </p>
 *
 * @since 2019-04-19
 */
public interface IPmsSkuStockService extends IService<PmsSkuStock> {
    /**
     * 根据产品id和skuCode模糊搜索
     */
    List<PmsSkuStock> getList(Long pid, String keyword);

    /**
     * 批量更新商品库存信息
     */
    int update(Long pid, List<PmsSkuStock> skuStockList);
}
